$(document).ready(function () {
    $("#generate_login").on('click', function (e) {
        e.preventDefault();
        var userName = $("#name").val();
        if (userName.split(' ').length < 3) {
            alert("Сначала введите полное ФИО пользователя");
            return;
        }
        $.ajax({
            type: "POST",
            url: "/register/generate",
            data: {name: userName},
            success: function (data) {
                $("#login").val(data);
            },
            error: function (data) {
                alert("Ошибка при генерации");
            }
        });
    });
    $('#reportType').on('change', function (e) {
        var reportType = $(this).val(),
            $reportParams = $('#reportParams'),
            $semestrRange = $('#semestrRange'),
            $customRange = $('#customRange');

        switch (reportType) {
            case "totalMark_range":
            case "byEvents_range":
                $reportParams.removeClass('hide');
                $semestrRange.addClass('hide');
                $customRange.removeClass('hide');
                break;
            case "totalMark":
            case "byEvents":
                $reportParams.removeClass('hide');
                $customRange.addClass('hide');
                $semestrRange.removeClass('hide');
                break;
            case "usersToEvent":
                $reportParams.removeClass('hide');
                $customRange.addClass('hide');
                $semestrRange.removeClass('hide');
                break;
            case "-1":
                $reportParams.addClass('hide');
                break;
            default:
                throw Error("Unknown report type");
        }
        footerFix();
    });
    var err = function (err) {
        $('#rangeError').text("Нету событий в этом промежутке!");
    };
    var downloadFile = function (data) {
        if (data) {
            data.reportType = $('#reportType').val();
            window.location.href = "/download/report/" +
            "?reportType=" + data.reportType +
            "&fromDate=" + data.fromDate +
            "&toDate=" + data.toDate;
        }
    };
    $('#downloadRatingBySemestrRange').on('click', function (e) {
        e.preventDefault();
        var eventData = {};
        eventData.semestrNumber = $('#semestrNumber').val();
        eventData.year = $('#year').val();
        $.ajax({
            url: '/events/checkExistence',
            method: 'get',
            data: eventData
        }).success(downloadFile).error(err);
    });

    function getMillis(datePicker) {
        return new Date(datePicker.data('datepicker').date).getTime();
    }

    $("#fromDate, #toDate").datepicker({
        "format": "dd.mm.yyyy",
        "setDate": new Date(),
        autoclose: true,
        todayHighlight: true
    });
    $('#downloadRatingByCustomRange').on('click', function (e) {
        e.preventDefault();
        var eventData = {};
        eventData.fromDate = getMillis($('#fromDate'));
        eventData.toDate = getMillis($('#toDate'));
        $.ajax({
            url: '/events/checkExistenceForRange',
            method: 'get',
            data: eventData
        }).success(downloadFile).error(err);
    });

});