$("#page-order,#page-sort,#page-no ").change(function () {
    filter();
});

function filter() {
    var form = $("#filterForm");
    form.find("input[name=sort]").val($("#page-sort").val());
    var page = $("#page-no").val();
    if (page === undefined || page === null || page < 1) {
        page = 1;
    }
    form.find("input[name=order]").val($("#page-order").val());
    form.find("input[name=page]").val(page - 1);
    form.submit();
};

function getCsrfToken() {
    return $("meta[name='_csrf']").attr("content");
};
