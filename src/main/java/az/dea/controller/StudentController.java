package az.dea.controller;

import az.dea.data.StudentRepository;
import az.dea.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getStudentList(){
        ModelAndView model = new ModelAndView("index");
        try {
            List<Student> students = studentRepository.getStudentList();
//            for (Student student : students) {
//                System.out.println(student);
//            }
            model.addObject("students", students);
        }catch (Exception e){
            e.printStackTrace();
        }
        return model;
    }
    @ResponseBody
    @RequestMapping(value = "/addStudent", method = {RequestMethod.POST, RequestMethod.GET})
    public void addStudent(HttpServletRequest request){
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String parent = request.getParameter("parent");
        String birthDate = request.getParameter("birthDate");
        String phone = request.getParameter("phone");
        String gmail = request.getParameter("gmail");
        String gmailCode = request.getParameter("gmailCode");
        String gender = request.getParameter("gender");

        System.out.println(name+surname+parent+birthDate+phone+gmail+gmailCode+gender);

    }
}
