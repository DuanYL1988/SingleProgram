function showMenu(element) {
    $(".parentMenu > ul").css("display", "none");
    let thisUl = $(element).find("ul")[0]
    $(thisUl).css("display", "contents");
}