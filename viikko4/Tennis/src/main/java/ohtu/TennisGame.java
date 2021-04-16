package ohtu;

public class TennisGame {

   
    private int player1Points = 0;
    private int player2Points = 0;
    
    public TennisGame() {
        player1Points = 0;
        player2Points = 0;
    }

    public void wonPoint(String playerName) {
        
        if (playerName == "player1")
        player1Points += 1;
        else
        player2Points += 1;
    }

    public String getScore() {
        String score = "";
        int tempScore=0;
        if (player1Points==player2Points)
        {
            switch (player1Points)
            {
                case 0:
                        score = "Love-All";
                    break;
                case 1:
                        score = "Fifteen-All";
                    break;
                case 2:
                        score = "Thirty-All";
                    break;
                case 3:
                        score = "Forty-All";
                    break;
                default:
                        score = "Deuce";
                    break;
                
            }
        }
        else if (player1Points>=4 || player2Points>=4)
        {
            int minusResult = player1Points-player2Points;
            if (minusResult==1) score ="Advantage player1";
            else if (minusResult ==-1) score ="Advantage player2";
            else if (minusResult>=2) score = "Win for player1";
            else score ="Win for player2";
        }
        else
        {
            for (int i=1; i<3; i++)
            {
                if (i==1) tempScore = player1Points;
                else { score+="-"; tempScore = player2Points;}
                switch(tempScore)
                {
                    case 0:
                        score+="Love";
                        break;
                    case 1:
                        score+="Fifteen";
                        break;
                    case 2:
                        score+="Thirty";
                        break;
                    case 3:
                        score+="Forty";
                        break;
                }
            }
        }
        return score;
    }
}