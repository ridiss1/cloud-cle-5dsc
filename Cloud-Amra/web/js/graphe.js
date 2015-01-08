window.onload = function(){
var ramdata110 = [
{
value: 210,
color:'#F7464A',
highlight: '#FF5A5E',
label: 'RAM Usage'
},
{
value: 400,
color: '#4D5360',
highlight: '#616774',
label: 'RAM Total'
}
];
var cpudata110 = [
{
value: 70,
color:'#F7464A',
highlight: '#FF5A5E',
label: 'CPU Usage'
},
{
value: 540,
color: '#4D5360',
highlight: '#616774',
label: 'CPU Total'
}
];
var memdata110 = [
{
value: 10,
color:'#F7464A',
highlight: '#FF5A5E',
label: 'Disk Usage'
},
{
value: 600,
color: '#4D5360',
highlight: '#616774',
label: 'Disk Total'
}
];
var ctx = document.getElementById('ramdata110').getContext('2d');
window.myPieramdata110 = new Chart(ctx).Pie(ramdata110);
var ctx = document.getElementById('cpudata110').getContext('2d');
window.myPiecpudata110 = new Chart(ctx).Pie(cpudata110);
var ctx = document.getElementById('memdata110').getContext('2d');
window.myPiememdata110 = new Chart(ctx).Pie(memdata110);
};
