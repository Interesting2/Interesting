const Player_One = "X";
const Player_Two = "O";

class TicTacToeGame {

  handleSquareClick(event) {
    //console.log(event.target.id);
    this.executeMove(event.target.id);
  }
  
  
  
  executeMove(moveIndex) {

    if (this.board[moveIndex] === "") {  // valid move
      this.board[moveIndex] = this.currentPlayer;
      this.updateBoard();
    //  console.log(this.gameEnd());
    
      if (!this.gameEnd()) {         // if game end
        
       // console.log("hi");
        this.currentPlayer = (this.currentPlayer == Player_One ? Player_Two : Player_One);
        

      }
      else if (this.gameEnd() === 2) {
        alert("It's a Tie! ");
        console.log("Tie");
        this.start();
      }
      else {
        
        alert("Player " + this.currentPlayer + " is the triumph!");
        console.log("Game Ended");
        this.start();
      }
      
      console.log(this.board);
    }
  }
  
  
  
  updateBoard() {
    let gameBoard = document.getElementById("gameBoard");
    let squareElements = gameBoard.childNodes;
    
    squareElements.forEach((element, index) => {
      if (element.innerText != this.board[index] ) {
        element.innerText = this.board[index];           // update in HTML
      }
    });
    //console.log(squareElements);
  }


  
  gameEnd() {
    
    const winningCombos = [
      
      [0,1,2], [3, 4, 5], [6, 7, 8],
      [0,3,6], [1,4,7], [2,5,8],
      [0,4,8], [2,4,6]  
      
    ];
    let countTie = 0;
    for (let i = 0; i < this.board.length; i ++) {
      if (this.board[i] !== "") countTie ++;
    }
   // console.log(countTie);
    if (countTie == this.board.length) return 2;
    
    return winningCombos.find(combo => {
      if (this.board[combo[0]] !== "" && this.board[combo[1]] !== "" && this.board[combo[2]] !== ""
         && this.board[combo[0]] === this.board[combo[1]] &&  this.board[combo[1]] === this.board[combo[2]]) {
        return 1;
      }
      else return false;
    });
  }



  drawBoard() {
    document.body.innerHTML = "";
    let gameBoard = document.createElement('div');
    gameBoard.id= 'gameBoard';
    gameBoard.classList.add("board");   // create class

    gameBoard.addEventListener('click', this.handleSquareClick.bind(this))


    this.board.forEach( (square, index) =>  {
      console.log(index);
      let squareElement = document.createElement("div");
      squareElement.id = index;
      squareElement.classList.add("square");
      
      gameBoard.appendChild(squareElement)
    });

    document.body.appendChild(gameBoard);

  }
  
  
  
  start() {
    this.board = ["","","",
                  "","","",
                  "","",""];
      
    this.currentPlayer = Player_One;

    this.drawBoard();

  }
}







const main = new TicTacToeGame();
main.start();
