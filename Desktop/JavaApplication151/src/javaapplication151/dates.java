package javaapplication151;

public class dates {

    protected int day;
    protected int month;
    protected int year;

    public dates(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int year() {
        return year;
    }

    public int daysto(dates obj) {
        int passedDay = obj.day;        
        int passedMonth = obj.month;
        int dayInMonth = Math.abs(this.month - passedMonth) * 30;
        int yearsPassed = Math.abs(this.year - obj.year); 
        int difference = Math.abs(passedDay - this.day) + dayInMonth + yearsPassed * 365; 
        return difference;
        
        
        
    }

    
   
}
