package view;

import java.util.Scanner;

public class AdminView {

    static PointView pointView ;
    static StudentView studentView;

    static ClassView classView;

    static ModuleView moduleView;
    public static Scanner scanner = new Scanner(System.in);
    public AdminView(){
        pointView = new PointView();
        studentView = new StudentView();
        classView = new ClassView();
        moduleView = new ModuleView();
    }
        public void menuView(){
            System.out.println("                    ╔═══════════════════════════════════════════════╗");
            System.out.println("                    ║                       Menu                    ║");
            System.out.println("                    ╠═══════════════════════════════════════════════╣");
            System.out.println("                    ║               1. Quản Lý Sinh Viên            ║");
            System.out.println("                    ║               2. Quản Lý Điểm                 ║");
            System.out.println("                    ║               3. Quản lý Lớp Học              ║");
            System.out.println("                    ║               4. Quản lý Module               ║");
            System.out.println("                    ║               5. Exits                        ║");
            System.out.println("                    ╚═══════════════════════════════════════════════╝");
            System.out.print("Vui lòng chọn một lựa chọn: ");

            boolean flap = false;
            do {
                try{
                    int actionChoice = Integer.parseInt(scanner.nextLine());
                    switch (actionChoice){
                        case 1 :
                            studentView.launcherStudent();
                            flap = false;
                            break;
                        case 2:
                            pointView.launcherPoint();
                            flap = false;
                            break;
                        case 3:
                            classView.launcherClass();
                            flap = false;
                            break;
                        case 4:
                            moduleView.launcherCourse();
                            flap = false;
                            break;
                        case 5:
                            System.out.println("BYE BYE!!!");
                            flap = false;
                            break;
                        default:
                            System.out.println("chỉ nhập 1 trong các tác vụ");
                            flap = true;
                            break;
                    }
                }catch (NumberFormatException e){
                    System.out.println("Nhập sai định dạng vui lòng nhập lại");
                    System.out.print("Vui lòng chọn một lựa chọn: ");
                    flap=true;
                }

            }while (flap);

        }


}

