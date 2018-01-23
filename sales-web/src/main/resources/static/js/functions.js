/**
 * Created by xavier on 2018/1/23.
 */
function quitLogin() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/sales/api/quit", false);
    xhr.send(null);
    location.reload(true);
}
