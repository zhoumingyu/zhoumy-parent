$(function() {
	var settings = {
		url : ctx + "userwali/list",
		pageSize : 10,
		queryParams : function(params) {
			return {
				pageSize : params.limit,
				pageNum : params.offset / params.limit + 1,
				username : $(".user-table-form").find("input[name='username']")
						.val().trim(),
				ssex : $(".user-table-form").find("select[name='ssex']").val(),
				status : $(".user-table-form").find("select[name='status']")
						.val()
			};
		},
		columns : [ {
			checkbox : true
		}, {
			field : 'id',
			visible : false
		}, {
			field : 'phone',
			title : '手机'
		}, {
			field : 'createTime',
			title : '创建时间'
		}

		]
	}
	$MB.initTable('userTable', settings);
});

function search() {
	$MB.refreshTable('userTable');
}

function refresh() {
	$(".user-table-form")[0].reset();
	$MB.refreshTable('userTable');
}

function exportUserExcel() {
	$.post(ctx + "userwali/excel", $(".user-table-form").serialize(), function(
			r) {
		if (r.code == 0) {
			window.location.href = "common/download?fileName=" + r.msg
					+ "&delete=" + true;
		} else {
			$MB.n_warning(r.msg);
		}
	});
}

function exportUserCsv() {
	$.post(ctx + "userwali/csv", $(".user-table-form").serialize(),
			function(r) {
				if (r.code == 0) {
					window.location.href = "common/download?fileName=" + r.msg
							+ "&delete=" + true;
				} else {
					$MB.n_warning(r.msg);
				}
			});
}