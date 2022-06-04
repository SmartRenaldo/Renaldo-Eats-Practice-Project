//提交订单
function  addOrderApi(data){
    return $axios({
        'url': '/orders/submit',
        'method': 'post',
        data
      })
}

//查询所有订单
function orderListApi() {
  return $axios({
    'url': '/orders/list',
    'method': 'get',
  })
}

//分页查询订单
function orderPagingApi(data) {
  return $axios({
      'url': '/orders/page',
      'method': 'get',
      params:{...data}
  })
}

//再来一单
function orderAgainApi(data) {
  return $axios({
      'url': '/orders/again',
      'method': 'post',
      data
  })
}