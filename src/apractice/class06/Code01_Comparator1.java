package apractice.class06;

import java.util.Arrays;
import java.util.Comparator;

public class Code01_Comparator1 {
    public static class Student {
        private int id;
        private int age;
        private String name;

        Student(int id,int age,String name) {
            this.id = id;
            this.age = age;
            this.name = name;
        }
    }

    public static class StudentConparator implements Comparator<Student> {

        @Override
        public int compare(Student o2,Student o1) {
            return (o1.id != o2.id) ? (o1.id - o2.id) : (o1.age - o2.age) ;
        }
    }

    public static void main(String[] args) {
        Student t1 = new Student(2,10,"Lisa");
        Student t2 = new Student(3,10,"Helle");
        Student t3 = new Student(1,9,"Demi");
        Student[] students = new Student[]{t1,t2,t3};
        System.out.println("排序前");
        for(Student st: students) {
            System.out.println(st.name);
        }
        Arrays.sort(students,new StudentConparator());
        System.out.println("排序后");
        for(Student st: students) {
            System.out.println(st.name);
        }
    }
}
