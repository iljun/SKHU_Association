$(function() {
    $("tr[data-url]").click(function() {
        location.href = $(this).attr("data-url");
    });
});