function loginApi(data) {
    return $axios({
      'url': '/customers/login',
      'method': 'post',
      data
    })
  }

function loginoutApi() {
  return $axios({
    'url': '/customers/loginout',
    'method': 'post',
  })
}

  