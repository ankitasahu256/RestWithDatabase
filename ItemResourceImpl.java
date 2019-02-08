package com.giantflyingsaucer.restjson.v1.resources.impl;

import com.giantflyingsaucer.restjson.v1.resources.Item;
import com.giantflyingsaucer.restjson.v1.resources.ItemResource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Path;

// Set the path, version 1 of API
@Path("/v2/item")
public class ItemResourceImpl implements ItemResource {
     List<Item> items = new ArrayList<Item>();
    @Override
    public List<Item> getItems() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Item", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product ");
           
            while (rs.next()) {
                items.add(new Item(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
            return items;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ItemResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ItemResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
