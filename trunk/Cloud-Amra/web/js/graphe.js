window.onload = function(){
var ramdata200 = [
{
value: 15679488,
color:'#F7464A',
highlight: '#FF5A5E',
label: 'RAM Usage'
},
{
value: 268435456,
color: '#46BFBD',
highlight: '#5AD3D1',
label: 'RAM Total'
}
];
var cpudata200 = [
{
value: 0.0142027604253806,
color:'#F7464A',
highlight: '#FF5A5E',
label: 'CPU Usage'
},
{
value: 1,
color: '#46BFBD',
highlight: '#5AD3D1',
label: 'CPU Total'
}
];
var memdata200 = [
{
value: 404045824,
color:'#F7464A',
highlight: '#FF5A5E',
label: 'Disk Usage'
},
{
value: 5368709120,
color: '#46BFBD',
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
