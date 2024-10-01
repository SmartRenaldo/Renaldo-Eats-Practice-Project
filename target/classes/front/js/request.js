(function (win) {
  axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
  const service = axios.create({
    baseURL: '/',
    timeout: 600000
  })

  service.interceptors.request.use(config => {
    // Whether to set the token
    // const isToken = (config.headers || {}).isToken === false
    // if (getToken() && !isToken) {
    // // Each request carries a custom token, please modify it according to the actual situation
    //   config.headers['Authorization'] = 'Bearer ' + getToken()
    // }
    // get request mapping params parameter
    if (config.method === 'get' && config.params) {
      let url = config.url + '?';
      for (const propName of Object.keys(config.params)) {
        const value = config.params[propName];
        var part = encodeURIComponent(propName) + "=";
        if (value !== null && typeof(value) !== "undefined") {
          if (typeof value === 'object') {
            for (const key of Object.keys(value)) {
              let params = propName + '[' + key + ']';
              var subPart = encodeURIComponent(params) + "=";
              url += subPart + encodeURIComponent(value[key]) + "&";
            }
          } else {
            url += part + encodeURIComponent(value) + "&";
          }
        }
      }
      url = url.slice(0, -1);
      config.params = {};
      config.url = url;
    }
    return config
  }, error => {
      Promise.reject(error)
  })

  // response interceptor
  service.interceptors.response.use(res => {
      console.log('---response interceptor---',res)
      if (res.data.code === 0 && res.data.msg === 'NOTLOGIN') {
        window.top.location.href = '/front/page/login.html'
      } else {
        return res.data
      }
    },
    error => {
      let { message } = error;
      if (message == "Network Error") {
        message = "Backend interface connection exception";
      }
      else if (message.includes("timeout")) {
        message = "System interface request timeout";
      }
      else if (message.includes("Request failed with status code")) {
        message = "System interface error: " + message.substr(message.length - 3);
      }
      window.vant.Notify({
        message: message,
        type: 'warning',
        duration: 5 * 1000
      })
      //window.top.location.href = '/front/page/no-wify.html'
      return Promise.reject(error)
    }
  )
  win.$axios = service
})(window);
