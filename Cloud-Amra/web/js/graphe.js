window.onload = function(){
var ramdata200 = [
{
value: 25640960,
color:'#c4e3f3',
highlight: '#FF5A5E',
label: 'RAM Usage'
},
{
value: 268435456,
color: 'darkblue',
highlight: '#5AD3D1',
label: 'RAM Total'
}
];
var cpudata200 = [
{
value: 0.00161986401022538,
color:'#c4e3f3',
highlight: '#FF5A5E',
label: 'CPU Usage'
},
{
value: 1,
color: 'darkblue',
highlight: '#5AD3D1',
label: 'CPU Total'
}
];
var memdata200 = [
{
value: 404062208,
color:'#c4e3f3',
highlight: '#FF5A5E',
label: 'Disk Usage'
},
{
value: 5368709120,
color: 'darkblue',
highlight: '#5AD3D1',
label: 'Disk Total'
}
];
var ctx = document.getElementById('ramdata200').getContext('2d');
window.myPieramdata200 = new Chart(ctx).Pie(ramdata200);
var ctx = document.getElementById('cpudata200').getContext('2d');
window.myPiecpudata200 = new Chart(ctx).Pie(cpudata200);
var ctx = document.getElementById('memdata200').getContext('2d');
window.myPiememdata200 = new Chart(ctx).Pie(memdata200);
};
