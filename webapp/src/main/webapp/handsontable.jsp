<!DOCTYPE html>
<html>
<head>
    <title>Handsontable</title>
    <script src="/dist/handsontable.full.js"></script>
    <script src="/jquery/jquery-3.2.1.js"></script>
    <link rel="stylesheet" href="/dist/handsontable.full.css"/>
</head>
<body>
<p>
    <button name="load" id="load" onclick="load()">Load</button>
    <button name="save" id="save" onclick="save()">Save</button>
</p>
<div id="example1"></div>

<script>

    function getData() {
        return [
            ['', 'Kia', 'Nissan', 'Toyota', 'Honda'],
            ['2008', 10, 11, 12, 13],
            ['2009', 20, 11, 14, 13],
            ['2010', 30, 15, 12, 13]
        ];
    }

    var example1 = document.getElementById('example1'),
        settings1,
        hot;

    settings1 = {
        data: getData(),
        startRows: 5,
        startCols: 5,
        minRows: 5,
        minCols: 5,
//        maxRows: 10,
//        maxCols: 10,
        manualColumnResize: true,
        manualRowResize: true,
        rowHeaders: true,
        colHeaders: true,
        minSpareRows: 1,
        manualColumnMove: true,
        manualRowMove: true,
        mergeCells: [],
        contextMenu: true
    };
    hot = new Handsontable(example1, settings1);



    function save() {

        var cellJson = new Object();
        cellJson.dataInfo = hot.getData();
        cellJson.mergeInfo = hot.mergeCells.mergedCellInfoCollection;
        var dataJson = JSON.stringify({data: cellJson})
    }

    function load() {
        var aa = {
            "data": {
                "dataInfo": [["", "Kia", "Nissan", "Toyota", "Honda"], ["2008", 10, 11, 12, 13], ["2009", 20, 11, 14, 13], ["2010", 30, 15, 12, 13], [null, null, null, null, null]],
                "mergeInfo": [{"row": 1, "col": 2, "rowspan": 1, "colspan": 2}]
            }
        }
        var mergeInfo = aa.data.mergeInfo
        for (var i = 0; i < mergeInfo.length; i++) {
            settings1.mergeCells.push(mergeInfo[i]);
        }
        settings1.data = aa.data.dataInfo
        hot.destroy()
        hot = new Handsontable(example1, settings1);
        hot.render()
    }
</script>
</body>