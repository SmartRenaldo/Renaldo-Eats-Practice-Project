function isValidUsername(str) {
    return ['admin', 'editor'].indexOf(str.trim()) >= 0;
}

function isExternal(path) {
    return /^(https?:|mailto:|tel:)/.test(path);
}

function isCellPhone(val) {
    if (!/^0?\d{9}$/.test(val)) {
        return false
    } else {
        return true
    }
}

function checkUserName(rule, value, callback) {
    if (value == "") {
        callback(new Error("Please enter the username"))
    } else {
        callback()
    }
}

function checkName(rule, value, callback) {
    if (value == "") {
        callback(new Error("Please enter the name"))
    } else {
        callback()
    }
}

function checkPhone(rule, value, callback) {
    // let phoneReg = /(^1[3|4|5|6|7|8|9]\d{9}$)|(^09\d{8}$)/;
    if (value == "") {
        callback(new Error("Please enter the phone"))
    } else if (!isCellPhone(value)) {
        callback(new Error("Please enter a valid phone number!"))
    } else {
        callback()
    }
}
