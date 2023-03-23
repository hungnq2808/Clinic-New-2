/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
/**
 *
 * @author FOR GAMER
 */
public class Medicine {
    int id;
    String name;
    float price;
    boolean status;
    String description;
    int quantity;
    Date expiry_date;

    public Medicine(int id, String name, float price,Date expiry_date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.expiry_date = expiry_date;
    }
    
}


