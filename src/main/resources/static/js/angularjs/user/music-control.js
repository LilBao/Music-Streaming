$scope.isPlaying = false;
$scope.isRandom = false;
$scope.isRepeat = false;
$scope.isMute = false;
$scope.QueueAudio = [];
$scope.duration = $('#duration-audio');
$scope.audio = $('#audio');

$scope.Queue = function(item){
    QueueAudio.push(item);
}