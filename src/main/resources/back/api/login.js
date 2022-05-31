function loginApi(data) {
  return $axios({
    'url': '/employees/login',
    'method': 'post',
    data
  })
}

function logoutApi(){
  return $axios({
    'url': '/employees/logout',
    'method': 'post',
  })
}
