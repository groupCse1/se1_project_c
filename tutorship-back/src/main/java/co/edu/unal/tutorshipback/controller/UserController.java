
package co.edu.unal.tutorshipback.controller;

import co.edu.unal.tutorshipback.model.User;
import co.edu.unal.tutorshipback.repository.UserRepository;
import co.edu.unal.tutorshipback.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @GetMapping("/user/username/{id}")
    public User getuserbyUsername(@PathVariable(value = "id") String userId){
        return userRepository.finduserbyUsername(userId);
        }
    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId,
                           @Valid @RequestBody User userDetails) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userDetails.getName());
        user.setFaculty(userDetails.getFaculty());
        user.setCareer(userDetails.getCareer());
        user.setCellphone(userDetails.getCellphone());
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/inicio")
    @ResponseBody
    public boolean VerifyIn (@Valid @RequestBody User userIn){
        String sqlA = "SELECT * FROM user where user = ?";


        String user = userIn.getUser();
        String password = userIn.getPassword();
        String name = userIn.getName();
        String faculty = userIn.getFaculty();
        String career = userIn.getCareer();



        List<User> ul = jdbcTemplate.query(sqlA, new Object[]{user}, (rs,rowNum)-> new User(
                rs.getString("user"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("faculty"),
                rs.getString("career")
        ));
        User u = new User();

        if (ul.isEmpty()==false){
            u = ul.get(0);
            if (u.getPassword().equals(password)){
                return true;
            }else{
                System.out.println("No es posible encontrar usuario y/o contraseña");
            }
        }
        return false;

    }

    @RequestMapping(value="/init")
    @ResponseBody
    public User GetUserInfo (String user){
        String sql="SELECT * FROM user where user = ?";
        User ul = jdbcTemplate.queryForObject(sql, new Object[]{user}, (rs,rowNum)-> new User(
                rs.getString("user"),
                rs.getString("name"),
                rs.getString("faculty"),
                rs.getString("career"),
                rs.getLong("cellphone")
        ));
        return ul;
    }
}
