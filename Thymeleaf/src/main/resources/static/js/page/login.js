function login(type) {
    var form = document.getElementById('loginForm')
    var formData = new FormData(form)
    console.dir(formData)
    form.submit();
}