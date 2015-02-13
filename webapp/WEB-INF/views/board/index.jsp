<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <script type="text/javascript">

        $(document).ready(function () {

            $('#btn_search').click(function () {
                var select_type = $("#select_type").val();
                var keyword = $("#keyword").val();

                $("#jqGrid").jqGrid('setGridParam', {
                    url: '/api/board/list',
                    postData : {
                        "select_type" : select_type,
                        "keyword" : keyword
                    }
                }).trigger("reloadGrid");

            });

            $("#jqGrid").jqGrid({
                url: '/api/board/list',
                mtype: "GET",
                datatype: "json",
                jsonReader: {
                    root: "jqGrid.rows",
                    repeatitems : false,
                    id: "id"
                },

                page: 1,
                rowNum:10,
                rowList:[10,20,30],
                sortname: 'id',
                sortorder: "desc",

                caption: "캡션",
                colNames: ['키', '제목', '내용', '등록일'],
                colModel: [
                    { name: 'id', key: true, width: 75 },
                    { name: 'title', width: 150 },
                    { name: 'desc', width: 150 },
                    { name: 'createDt', width: 150 },
                ],

                gridview : true, // 반드시 주도록 하자. 주고 안주고 속도 차이가 심하다.
                viewrecords: true,

                width: 750,
                height: 250,
                pager: "#jqGridPager",

                onSelectRow: function(rowid, selected) {
                    $('#log').prepend('클릭 rowid + ' + rowid + ' ' + 'selected + ' + selected + "<br/><br/>");
                },

                gridComplete: function() {
                    $('#log').prepend('그리드 그리기 완료<br/><br/>');
                },

                loadError: function(xhr, status, error) {
                    $('#log').prepend('HTTP status code: ' + jqXHR.status + ' ' + 'textStatus: ' + textStatus + ' ' + 'errorThrown: ' + errorThrown + '<br/>');
                    $('#log').prepend('HTTP message body (jqXHR.responseText): ' + jqXHR.responseText + "<br/><br/>");
                }

            });

            $('#jqGrid').jqGrid('navGrid',"#jqGridPager", {
                search: false,
                add: false,
                edit: false,
                del: false,
                refresh: true
            });
        });

        function selectRow() {
            jQuery('#jqGrid').jqGrid('setSelection', '1');
        }
    </script>
</head>
<body>

    <div>
        <select id="select_type">
            <option value="">전체</option>
            <option value="title">제목</option>
            <option value="desc">내용</option>
        </select>
        <input type="text" id="keyword" value="" />
        <input type="button" id="btn_search" value="검색" />

    </div>

    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>

    <button type="button" onclick="selectRow();">키1 로 로우 선택</button>

    <div id="log"></div>
</body>
</html>
