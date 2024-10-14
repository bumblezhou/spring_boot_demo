package com.example.springboot.demo.config;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.springboot.demo.model.Department;
import com.example.springboot.demo.model.ProcurementType;
import com.example.springboot.demo.model.Product;
import com.example.springboot.demo.model.ProductType;
import com.example.springboot.demo.model.Role;
import com.example.springboot.demo.model.Supplier;
import com.example.springboot.demo.model.User;
import com.example.springboot.demo.model.UserRoleMapping;
import com.example.springboot.demo.repository.DepartmentRepository;
import com.example.springboot.demo.repository.ProcurementTypeRepository;
import com.example.springboot.demo.repository.ProductRepository;
import com.example.springboot.demo.repository.ProductTypeRepository;
import com.example.springboot.demo.repository.RoleRepository;
import com.example.springboot.demo.repository.SupplierRepository;
import com.example.springboot.demo.repository.UserRepository;
import com.example.springboot.demo.repository.UserRoleMappingRepository;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleMappingRepository userRoleMappingRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProcurementTypeRepository procurementTypeRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.count() == 0) {
            departmentRepository.save(new Department(null, "财务部", "1区11-1", "001", 5L, true));
            departmentRepository.save(new Department(null, "市场部", "2区11-1", "002", 25L, true));
            departmentRepository.save(new Department(null, "财务部", "3区11-1", "003", 5L, false));
            departmentRepository.save(new Department(null, "财务部", "4区11-1", "004", 5L, false));
            departmentRepository.save(new Department(null, "财务部", "5区11-1", "005", 5L, false));
            departmentRepository.save(new Department(null, "财务部", "6区11-1", "006", 5L, false));
            departmentRepository.save(new Department(null, "财务部", "7区11-1", "007", 5L, false));
            departmentRepository.save(new Department(null, "财务部", "8区11-1", "008", 5L, false));
            departmentRepository.save(new Department(null, "财务部", "9区11-1", "009", 5L, false));
            departmentRepository.save(new Department(null, "企划部", "10区1-1", "010", 15L, true));
            departmentRepository.save(new Department(null, "研发部", "10区1-2", "011", 38L, true));
            departmentRepository.save(new Department(null, "财务部", "11区1-1", "012", 5L, false));
            departmentRepository.save(new Department(null, "财务部", "12区1-1", "013", 5L, false));
            departmentRepository.save(new Department(null, "财务部", "13区1-1", "014", 5L, false));
            departmentRepository.save(new Department(null, "财务部", "14区1-1", "015", 5L, false));
            departmentRepository.save(new Department(null, "财务部", "15区1-1", "016", 5L, false));
            departmentRepository.save(new Department(null, "财务部", "16区1-1", "017", 5L, false));
            departmentRepository.save(new Department(null, "财务部", "17区1-1", "018", 5L, false));
            
            userRepository.save(new User(null, "user1", "user1@gmail.com", "password1", ""));
            userRepository.save(new User(null, "user2", "user2@gmail.com", "password2", ""));
            userRepository.save(new User(null, "user3", "user3@gmail.com", "password3", ""));
            userRepository.save(new User(null, "user4", "user4@gmail.com", "password4", ""));
            userRepository.save(new User(null, "user5", "user5@gmail.com", "password5", ""));

            roleRepository.save(new Role(null, "SPL", "Supplier"));
            roleRepository.save(new Role(null, "PO", "Procurement Officer"));
            roleRepository.save(new Role(null, "PM", "Procurement Manager"));
            roleRepository.save(new Role(null, "CEO", "Chief Executive Officer"));
            roleRepository.save(new Role(null, "CFO", "Chief Financial Officer"));

            userRoleMappingRepository.save(new UserRoleMapping(null, 1L, 1L));
            userRoleMappingRepository.save(new UserRoleMapping(null, 2L, 2L));
            userRoleMappingRepository.save(new UserRoleMapping(null, 3L, 3L));
            userRoleMappingRepository.save(new UserRoleMapping(null, 4L, 4L));
            userRoleMappingRepository.save(new UserRoleMapping(null, 5L, 5L));

            productTypeRepository.save(new ProductType(null, "Electronics", "电子产品"));
            productTypeRepository.save(new ProductType(null, "Computers", "电脑"));
            productTypeRepository.save(new ProductType(null, "SmartHome", "智能家居"));
            productTypeRepository.save(new ProductType(null, "IndustrialAndScientific", "工业和科技"));
            productTypeRepository.save(new ProductType(null, "Softwares", "软件"));
            productTypeRepository.save(new ProductType(null, "Automotive", "汽车"));

            supplierRepository.save(new Supplier(null, "Datacolor", "China", "086-*****", "supply1@gmail.com", "https://www.datacolor.com/", "", new SimpleDateFormat("yyyy-MM-dd").parse("2010-01-01")));
            supplierRepository.save(new Supplier(null, "HP", "US", "010-*****", "supply2@gmail.com", "https://www.hp.com/", "", new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01")));
            supplierRepository.save(new Supplier(null, "Govee", "US", "010-*****", "supply3@gmail.com", "https://us.govee.com/", "", new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01")));
            supplierRepository.save(new Supplier(null, "PGN Bearings", "US", "010-*****", "supply4@gmail.com", "https://pgnbearings.com/", "", new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-01")));
            supplierRepository.save(new Supplier(null, "Microsoft", "US", "010-*****", "supply5@gmail.com", "https://microsoft.com/", "", new SimpleDateFormat("yyyy-MM-dd").parse("1985-01-01")));
            supplierRepository.save(new Supplier(null, "Pertronix", "US", "010-*****", "supply6@gmail.com", "https://pertronixbrands.com/", "", new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01")));
            
            productRepository.save(new Product(null, 1L, 1L, "Datacolor Spyder Print - Advanced Data Analysis and Calibration Tool for Optimal Print Results, Perfect for Photographers, Graphic Designers, and Printing Professionals", 340.00, "/img/p_1.jpg", "", "https://www.amazon.com/Datacolor-S4SR100-Spyder-Print/dp/B006UACRTG/ref=sr_1_1?dib=eyJ2IjoiMSJ9.lmOCVuKtwl5dnH_xklO7kQ.-jzbZWClVh26TSCjCuCkl_0P9j0g8pHXZ6iYTNaci-w&dib_tag=se&qid=1728875641&s=electronics&sr=1-1", ""));
            productRepository.save(new Product(null, 2L, 2L, "HP 17 Business Laptop, 17.3” HD+ Display, 11th Gen Intel Core i3-1125G4 Processor, 32GB RAM, 1TB SSD, Wi-Fi, HDMI, Webcam, Windows 11 Pro, Silver", 474.93, "/img/p_2.jpg", "", "https://www.amazon.com/HP-Business-Display-i3-1125G4-Processor/dp/B0C6NC4KRT/ref=sr_1_12?dib=eyJ2IjoiMSJ9.zvJPwCBlA703eT5dDzBcR0TyY6rSnOHKQzdK4Mga-1DjmucgBd-XVpTaTrHI5oXIglrH5H-5Q4CMRjskggCfCKVa13Ids0GUXd3AxEOvGD4mPAZMG_E9xiJ6VU-bqRnV-fwbAhbyfxpD9WEfNnDdEzOaFXNzOjW16YMxIT-YE43kEDPoZ-YHh017m1pVDyrVI6ge5w2qSKv5XxW7eVycV2VXNHKgUbWf2IYZNlFLAmR94DiIwM-StVw7uH-dTRgK2FVlfHNb6tmcWh5gMZcLgfbrTeKSNXmuxOXISRF7lrE.TABnJ1aN4WjJ8hLiHx1obcyt6lQBUOSkNWQYmGmNHA4&dib_tag=se&qid=1728876346&s=computers-intl-ship&sr=1-12&th=1", ""));
            productRepository.save(new Product(null, 3L, 3L, "Govee Smart Light Bulbs, Color Changing Light Bulb, Work with Alexa and Google Assistant, 16 Million Colors RGBWW, WiFi & Bluetooth LED Light Bulbs, Music Sync, A19, 800 Lumens, 4 Pack", 34.99, "/img/p_3.jpg", "", "https://www.amazon.com/Govee-Changing-Dynamic-Bluetooth-Assistant/dp/B09B7NQT2K/ref=lp_21217035011_1_2?pf_rd_p=53d84f87-8073-4df1-9740-1bf3fa798149&pf_rd_r=XQMZFEH8K3VZ0PSQECS5&sbo=RZvfv%2F%2FHxDF%2BO5021pAnSA%3D%3D&th=1", ""));
            productRepository.save(new Product(null, 4L, 4L, "PGN #40 Roller Chain - 10 Feet + 2 Free Connecting Links - Carbon Steel Chains for Bycicles, Mini Bikes, Motorcycles, Go-Karts, Home and Industrial Machinery - 239 Links", 20.45, "/img/p_4.jpg", "", "https://www.amazon.com/PGN-Roller-Chain-10feet-Connecting/dp/B07NPSZCK6/ref=sr_1_7?dib=eyJ2IjoiMSJ9.Nu76zBirYESdaEncrHARQyc-8ZG6yMgLWX9ZSclF4RF08C1dJVa2iqet_ltE-DeuprK8mRiEcTcAmfY2YkKn9k-JTRmWRoCVPnFSdt9ClMM8ZwEky5Pm2hIeAVl1zF9zKi3xFhJYwX5QBQAuZm890iwamtixZl30RJ-uf5jNPpkkv_qiCkxlRUfLBJ6V_spy6YFM_gcxjzwUyOhR7-_DdfHMo8o-Ugrdyk59MKxoTRiMXc-hwM4DsStbOoInmkNuH-zlqhJtfGZeGScsd2rnqTWbG7tZmC2qRoX0DEKXPIs.UOtlRX-Sxlyl8PvHbPSO_30Wxxj8KyrJH6i811AM_Nw&dib_tag=se&qid=1728893962&s=industrial-intl-ship&sr=1-7", ""));
            productRepository.save(new Product(null, 5L, 5L, "Microsoft System Builder | Windоws 11 Home | Intended use for new systems | Install on a new PC | Branded by Microsoft", 119.99, "/img/p_5.jpg", "", "https://www.amazon.com/Microsoft-Wind%D0%BEws-Home-OEM-DVD/dp/B09MYJ1R6L/ref=sr_1_1?dib=eyJ2IjoiMSJ9._tuVS25iGicEBX1gY34WuyimvFtumtfaK6LPtvdy3qcUKqsHYUJrJwLaa4F8tI2cYI2D9dvPElG5oADTVOSd88OcBSrlK0Ufz7tinBqUq-fRNIrNntdlYDOkowOYCOW9bQlvl1uKOgMmJslXqcHKFM-KrZ9SqFtOTHiWUMMwEX_AK4jGeEM1yMKptMoR0H4k147SZGNewzgQXIxXDBgD92xZEi2HJdcZ1T358ow6ufk.TJKbKMiig_1Uhzb6DxiKFIzHdo7qoXfXigVf_dnATkE&dib_tag=se&qid=1728894253&s=software-intl-ship&sr=1-1&th=1", ""));
            productRepository.save(new Product(null, 6L, 6L, "PerTronix 40011 Flame-Thrower 40,000 Volt 1.5 ohm Coil , Black", 35.03, "/img/p_6.jpg", "", "https://www.amazon.com/PerTronix-40011-Flame-Thrower-Volt-Coil/dp/B00199F2WW/ref=sr_1_7?dib=eyJ2IjoiMSJ9.0i3yOVqpdDykIT3sfj4B19k8-bkq6OQYPboCvi0Ds0jeh-3rUWfQuBTz3XNi1RhiBUI9GOF91mYvC5a-074rcsxPResZy_y2lf3iciy3L5LeopcsnOj0E3YAC_PVfAByJjgLRmLLJjUU9yS5SkRR6dv4y8uq6So2u8X021BW6NVyn6I1DtNbKR7uRyiVkl6KoBAF89cwjEPE0cUMMRZHreJIYF_QXMngTNk0esNJU1VUKtv4Rtk0eplawPKf1JQTUNeE3jUvDDKhTxnsHH7x21o2Z8JIIlsAAiX9CPmxyfU.HWvJuHcOZFHQffFBL8IjsHrMvHhU66ZMtvN3cm0Frw0&dib_tag=se&qid=1728894636&s=automotive-intl-ship&sr=1-7", ""));

            procurementTypeRepository.save(new ProcurementType(null, "SingleSource", "单一来源"));
            procurementTypeRepository.save(new ProcurementType(null, "Tendering", "招标"));

            System.out.println("Initial data loaded into SQLite");
        } else {
            System.out.println("Data already exists, skipping initialization.");
        }
    }
}
