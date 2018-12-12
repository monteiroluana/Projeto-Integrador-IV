var datasource = [{
  somaMes:35.77, mes:'jan', ano:'2018'
},{
  somaMes:123.57, mes:'fev', ano:'2018'
},{
  somaMes:77.5, mes:'mar', ano:'2018'
},{
  somaMes:98.7, mes:'abr', ano:'2018'
},{
  somaMes:35.77, mes:'mai', ano:'2018'
},{
  somaMes:123.57, mes:'jun', ano:'2018'
},{
  somaMes:77.5, mes:'jul', ano:'2018'
},{
  somaMes:98.7, mes:'ago', ano:'2018'
},{
  somaMes:35.77, mes:'set', ano:'2018'
},{
  somaMes:123.57, mes:'out', ano:'2018'
},{
  somaMes:77.5, mes:'nov', ano:'2018'
},{
  somaMes:98.7, mes:'dez', ano:'2018'
},{
  somaMes:18.77, mes:'jan', ano:'2017'
},{
  somaMes:100.57, mes:'fev', ano:'2017'
},{
  somaMes:80.5, mes:'mar', ano:'2017'
},{
  somaMes:82.7, mes:'abr', ano:'2017'
},{
  somaMes:55.77, mes:'mai', ano:'2017'
},{
  somaMes:12.57, mes:'jun', ano:'2017'
},{
  somaMes:25.5, mes:'jul', ano:'2017'
},{
  somaMes:46.7, mes:'ago', ano:'2017'
},{
  somaMes:78.77, mes:'set', ano:'2017'
},{
  somaMes:81.57, mes:'out', ano:'2017'
},{
  somaMes:82.5, mes:'nov', ano:'2017'
},{
  somaMes:93.7, mes:'dez', ano:'2017'
}];


var chart = new Taucharts.Chart({
    data: datasource,
    type: 'line',
    x: 'mes',
    y: 'somaMes',
    text: 'mes',
    color: 'ano',// there will be two lines with different colors on the chart
    guide: {
    	showAnchors: 'always'
    },
    plugins: [   
        Taucharts.api.plugins.get('tooltip')()
    ]
});
chart.renderTo('#line');


var chart = new Taucharts.Chart({
  data: datasource,
  type: 'bar',
  x: 'mes',
  y: 'somaMes',
  color:'ano',
  plugins: [   
    Taucharts.api.plugins.get('tooltip')()
  ]
});
chart.renderTo('#bar');



$(document).ready(function () {
   $.ajax({
        url: '/relatorio/ver', method: 'GET', success: function (data) {
            
            console.log(data);

        }
    });
});
