//custom trim
//Delete the spaces at the left and right ends, custom trim() method
function trim (str) {
  return str == undefined ? "" : str.replace(/(^\s*)|(\s*$)/g, "")
}

//Get the parameters above the url address
function requestUrlParam(argname){
  var url = location.href
  var arrStr = url.substring(url.indexOf("?")+1).split("&")
  for(var i =0;i<arrStr.length;i++)
  {
      var loc = arrStr[i].indexOf(argname+"=")
      if(loc!=-1){
          return arrStr[i].replace(argname+"=","").replace("?","")
      }
  }
  return ""
}
