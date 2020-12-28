// listen actions

console.log("background");

chrome.browserAction.onClicked.addListener(buttonClicked);
chrome.browserAction.onClicked.addListener(function() {

    chrome.tabs.create({'url': "https://www.google.com"}, function(tab) {
    });

});

function buttonClicked(tabPosition) { // when clicked
    let msg = {
        txt: "got it"
    }
    console.log(tabPosition.id);
    chrome.tabs.sendMessage(tabPosition.id, msg);
}


