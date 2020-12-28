chrome.runtime.onMessage.addListener(gotMessage);

function gotMessage(message, request, sender, sendResponse) {
    console.log(message.txt);
    if (message.txt === "got it") {
        
        let paragraphs = document.getElementsByTagName('p');
        for (elt of paragraphs) {
            elt.style['background-color'] = '#00FF00';
        }
        

    }
}
 
