function showDialog() {
    var modal = document.getElementById("dialogReport");
    modal.style.display = "block";
}
function closeDialog() {
    var modal = document.getElementById("dialogReport");
    modal.style.display = "none";
}

var modalDiv = document.createElement("div");
modalDiv.className = "modal";
modalDiv.id = "dialogReport";
modalDiv.innerHTML = `
<div class="modal-dialog modal-dialog-centered">
<div class="modal-content">
    <div class="modal-header">
        <h4 class="modal-title">Report</h4>
        <button type="button" class="btn-close" onclick="closeDialog()" data-bs-dismiss="modal"></button>
    </div>
    <div class="modal-body">
        <div class="mb-3">
            <label class="form-label">Report image or title</label>
            <div class="form-check">
                <input type="radio" class="form-check-input" id="radio1" name="optradio" value="option1" checked>error 1
                <label class="form-check-label" for="radio1"></label>
            </div>
            <div class="form-check">
                <input type="radio" class="form-check-input" id="radio2" name="optradio" value="option2">error 2
                <label class="form-check-label" for="radio2"></label>
            </div>
            <div class="form-check">
                <input type="radio" class="form-check-input" id="radio3" name="optradio" value="option3">error 3
                <label class="form-check-label" for="radio3"></label>
            </div>
        </div>
        <div class="mb-3 mt-3">
            <label for="comment">Report content:</label>
            <textarea class="form-control" rows="5" id="comment" name="text"></textarea>
        </div>
    </div>      
    <div class="modal-footer">
        <button class="btn btn-danger" data-bs-dismiss="modal">Submit</button>
        <button class="btn btn-success" data-bs-dismiss="modal" onclick="closeDialog()">Cancel</button>
    </div>
</div>
</div>
`;

document.body.appendChild(modalDiv);