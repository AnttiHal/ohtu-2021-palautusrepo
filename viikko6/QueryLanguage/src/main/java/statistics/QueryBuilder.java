package statistics;
import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.HasFewerThan;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;

public class QueryBuilder {
    Matcher matcher;
    


    public QueryBuilder() {
        this.matcher = new All();
    }
    
    public QueryBuilder playsIn(String team) {
        this.matcher = new And(matcher, new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String goals) {
        this.matcher = new And(matcher, new HasAtLeast(value, goals));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String goals) {
        this.matcher = new And(matcher, new HasFewerThan(value, goals));
        return this;
    }

    public QueryBuilder oneOf(Matcher m1, Matcher m2) {        
        this.matcher = new Or(m1,m2);
        
        return this;
    }

    public Matcher build() {        
        return this.matcher;
    }
}