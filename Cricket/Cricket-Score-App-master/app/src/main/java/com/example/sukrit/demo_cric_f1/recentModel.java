//package com.example.sukrit.demo_cric_f1;
//
///**
// * Created by Sukrit on 4/11/2017.
// */
//
//public class recentModel {
//
////    String date;
//    String team1;
//    String team2;
//    boolean matchStarted;
//    Long unique_id;
//    String innings_requirement;
//
//    public recentModel( String team1, String team2, boolean matchStarted,Long unique_id,String innings_requirement)
//    {
//       // this.date=date;
//        this.team1=team1;
//        this.team2=team2;
//        this.matchStarted=matchStarted;
//        this.unique_id=unique_id;
//        this.innings_requirement=innings_requirement;
//    }
//
//    public boolean isMatchStarted() {
//        return matchStarted;
//    }
//
////    public String getDate() {
////        return date;
////    }
////
////    public void setDate(String date) {
////        this.date = date;
////    }
//
//
//    public String getInnings_requirement() {
//        return innings_requirement;
//    }
//
//    public void setInnings_requirement(String innings_requirement) {
//        this.innings_requirement = innings_requirement;
//    }
//
//
//
//    public Long getUnique_id() {
//        return unique_id;
//    }
//
//    public void setUnique_id(Long unique_id) {
//        this.unique_id = unique_id;
//    }
//
//
//    public String getTeam1() {
//        return team1;
//    }
//
//    public void setTeam1(String team1) {
//        this.team1 = team1;
//    }
//
//    public String getTeam2() {
//        return team2;
//    }
//
//    public void setTeam2(String team2) {
//        this.team2 = team2;
//    }
//
//}

package com.example.sukrit.demo_cric_f1;

/**
 * Created by Sukrit on 4/11/2017.
 */

public class recentModel {

    //    String date;
    String team1;
    String team2;
    boolean matchStarted;
    String innings_requirement;
    String score;
    String type;

    public recentModel( String team1, String team2, boolean matchStarted,String innings_requirement,String score,String type)
    {
        this.team1=team1;
        this.team2=team2;
        this.matchStarted=matchStarted;
        this.innings_requirement=innings_requirement;
        this.score=score;
        this.type=type;
    }



    public String getInnings_requirement() {
        return innings_requirement;
    }

    public String getScore() {
        return score;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type=type;
    }




}

//
//package com.example.sukrit.demo_cric_f1;
//
///**
// * Created by Sukrit on 4/11/2017.
// */
//
//public class recentModel {
//
//    //    String date;
//    String team1;
//    String team2;
//    boolean matchStarted;
//    Long unique_id;
//    String innings_requirement;
//
//    public recentModel( String team1, String team2, boolean matchStarted,Long unique_id,String innings_requirement)
//    {
//        // this.date=date;
//        this.team1=team1;
//        this.team2=team2;
//        this.matchStarted=matchStarted;
//        this.unique_id=unique_id;
//        this.innings_requirement=innings_requirement;
//    }
//
//    public boolean isMatchStarted() {
//        return matchStarted;
//    }
//
////    public String getDate() {
////        return date;
////    }
////
////    public void setDate(String date) {
////        this.date = date;
////    }
//
//
//    public String getInnings_requirement() {
//        return innings_requirement;
//    }
//
//    public void setInnings_requirement(String innings_requirement) {
//        this.innings_requirement = innings_requirement;
//    }
//
//
//
//    public Long getUnique_id() {
//        return unique_id;
//    }
//
//    public void setUnique_id(Long unique_id) {
//        this.unique_id = unique_id;
//    }
//
//
//    public String getTeam1() {
//        return team1;
//    }
//
//    public void setTeam1(String team1) {
//        this.team1 = team1;
//    }
//
//    public String getTeam2() {
//        return team2;
//    }
//
//    public void setTeam2(String team2) {
//        this.team2 = team2;
//    }
//
//}
