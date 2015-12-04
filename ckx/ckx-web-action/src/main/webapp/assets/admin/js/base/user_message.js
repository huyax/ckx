(function ($) {
    $(function () {
        $('#userMsgForm').submit(function () {
            if (!$(this).form('validate')) {
                return;
            }
            $(this)._ajaxForm(function (r) {
                if (r.r) {
                    asyncbox.tips(r.m, 'success');
                } else {
                    asyncbox.tips(r.m, 'error');
                }
            });
        });
        $('#userPwdForm').submit(function () {
            if (!$(this).form('validate')) {
                return;
            }
            $(this)._ajaxForm(function (r) {
                if (r.r) {
                    $('#userPwdForm').clearForm();
                    asyncbox.tips(r.m, 'success');
                } else {
                    asyncbox.tips(r.e, 'error');
                }
            });
        });
    });
})(jQuery);