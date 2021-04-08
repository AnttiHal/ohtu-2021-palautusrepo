
package ohtu;

public class Player implements Comparable<Player>{
    private String name;
    private String team;
    private int goals;
    private int assists;
    private String nationality;

    public void setName(String name) {
        this.name = name;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public void setGoals(String goals) {
        this.goals = Integer.valueOf(goals);
    }
    public void setAssists(String assists) {
        this.assists = Integer.valueOf(assists);
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }



    public String getName() {
        return name;
    }
    public String getTeam() {
        return team;
    }
    public int getGoals() {
        return goals;
    }
    public int getAssists() {
        return assists;
    }
    public String getNationality() {
        return nationality;
    }
    
    @Override
    public String toString() {
        
        return String.format("%-20s",name) + " " + team + " " + String.format("%-2d",goals) + " + " + String.format("%-2d",assists) + " = " + (goals+assists);
    }

    @Override
    public int compareTo(Player comparePlayer) {
        int comparegoalsAndAssists=comparePlayer.getGoals()+comparePlayer.getAssists();
        /* For Ascending order*/
        return comparegoalsAndAssists-(this.goals+this.assists);
    }
      
}
