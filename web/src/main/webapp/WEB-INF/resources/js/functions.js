function Functions() {

}

Functions.isInteger = function (nVal) {
    return typeof nVal === "number" && isFinite(nVal) && nVal > -9007199254740992 && nVal < 9007199254740992 && Math.floor(nVal) === nVal;
};

function getName(str, outp) {
    if (str.lastIndexOf('\\')) {
        var i = str.lastIndexOf('\\') + 1;
    }
    else {
        var i = str.lastIndexOf('/') + 1;
    }
    var filename = str.slice(i);
    var res = filename;
    if (filename.length > 21) {
        res = res.slice(0, 10) + "..." + res.slice(res.lastIndexOf('.') - 4, res.length);
    }
    var uploaded = document.getElementById(outp);
    uploaded.innerHTML = res;
}
