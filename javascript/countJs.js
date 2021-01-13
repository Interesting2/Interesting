
let count = 0;
const hex = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, "A", "B", "C", "D", "E", "F"];

const value = document.querySelector("#value");
const btns = document.querySelectorAll(".btn");
console.log(btns);

btns.forEach(function (elem, index) {
  elem.addEventListener("click", function(btn) {
    const styles = btn.currentTarget.classList;
    if (styles.contains("Decrease")) count --;
    else if (styles.contains("Increase")) count ++;
    else if (styles.contains("Reset")) count = 0;
    else if (styles.contains("Increase10")) count += 10;
    else if (styles.contains("Decrease10")) count -= 10;
    else {

      let hexColor = "#";
      for (let i = 0; i < 6; i ++) {
        let randomNum = generateRandom();

        hexColor += hex[randomNum];
      }
      document.body.style.backgroundColor = hexColor;


    }


    if (count > 0) {
      value.style.color = "green";
    }
    else if (count < 0) {
      value.style.color = "red";
    }
    else value.style.color = "blue";

    value.textContent = count;
  });


  console.log(count);

});

function generateRandom() {
  return Math.floor(Math.random() * hex.length);

}
