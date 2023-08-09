package data;

import dto.UserDTO;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProviderDataLogin {
    @DataProvider
    public Iterator<Object[]> userDtoWrongPassword(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                UserDTO.builder().email("dykestones@gmail.com").password("123456AA").build()
        });
        list.add(new Object[]{
                UserDTO.builder().email("dykestones@gmail.com").password("123456aa").build()
        });
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> userDtoLogin(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                UserDTO.builder().email("dykestones@gmail.com").password("12131415$Aa").build()
        });
        list.add(new Object[]{
                UserDTO.builder().email("dykestones@gmail.com").password("12131415$Aa").build()
        });
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> userDtoCSV() {
        List<Object[]> list = new ArrayList<>();
        String path = "src/test/resources/dataLogin.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {
            String line = reader.readLine();
            while (line != null){
                String[] split = line.split(",");
                list.add(new Object[] { UserDTO.builder().email(split[0]).password(split[1]).build()});
                line = reader.readLine();
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        //  reader.close();
        return list.iterator();
    }


}