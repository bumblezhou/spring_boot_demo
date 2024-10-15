package com.example.springboot.demo.config;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.springboot.demo.model.Department;
import com.example.springboot.demo.model.Organization;
import com.example.springboot.demo.model.Product;
import com.example.springboot.demo.model.ProductType;
import com.example.springboot.demo.model.Role;
import com.example.springboot.demo.model.Supplier;
import com.example.springboot.demo.model.User;
import com.example.springboot.demo.model.UserRoleMapping;
import com.example.springboot.demo.repository.DepartmentRepository;
import com.example.springboot.demo.repository.OrganizationRepository;
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
    private SupplierRepository supplierRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.count() == 0) {
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

            System.out.println("Initial user data loaded into SQLite");
        } else {
            System.out.println("User data already exists, skipping initialization.");
        }

        if(departmentRepository.count() == 0) {
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
            departmentRepository.save(new Department(null, "行政部", "17区1-2", "019", 6L, true));
            departmentRepository.save(new Department(null, "信息部", "17区1-3", "020", 7L, true));
            departmentRepository.save(new Department(null, "安全部", "17区1-4", "021", 8L, true));
            departmentRepository.save(new Department(null, "人事部", "17区1-5", "022", 9L, true));
            
            System.out.println("Initial department data loaded into SQLite");
        } else {
            System.out.println("Department data already exists, skipping initialization.");
        }
        
        if(productRepository.count() == 0) {

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
            productRepository.save(new Product(null, 1L, 1L, "2024 Version Datacolor Spyder Monitor Calibration Tool: Ensures Accurate & Consistent Color for Photos & Online Content", 169.99, "/uploads/p_7.jpg", "Color “Surprises” Are a Thing of the Past: Datacolor’s exclusive DevicePreview TM Beta feature simulates what your photos can look like on other devices on your own calibrated screenCalibration for Today’s Digital Workflows: Spyder fully calibrates a wider than ever range of laptop/desktop displays, including OLED, mini-LED, and Apple Liquid Retina XDRFast & Easy Color Confidence: It only takes about 90 seconds to ensure an accurate color starting point for viewing and editingProfessional Results for Every Experience Level: Intuitive software and a pre-set calibration option make it easy for novices to get professional results while customizable calibration settings give professionals creative flexibilityAdaptable to Light Shifts: Ambient light sensor tracks/measures room light so you adjust your display brightness and contrast to ideal levels. The software can warn you to recalibrate or do it automatically using custom profiles based on light level.", "https://www.amazon.com/Version-Datacolor-Spyder-Monitor-Calibration/dp/B0DDYDLPJ4/ref=sr_1_1?crid=QSZMZ31R2JK0&dib=eyJ2IjoiMSJ9.dbJ7edaztZQNOKIZSZkqoTjNIOpYEYSXtTULB9NssFM4MgjM0cH3-ORXtm97ij8Nrd2hsbAgsvACJSCp1LCosRCISPW48wlkjxxTboEbToEWXeCrl9kLMRVJhz-Av6IH0yRZSZHcJh8x14f3yuI4NQ3vmDWlPHHDPU-MTTofgaTE22aPm2o6ETza8mueMLBnaXj3tJltG-Wl2P77RP2j3NYlImkk5kVxTP6OX78JrpI.lUAGXe7srungbKVIHnys5_fIa2wNQh_76O6A0K_WJT4&dib_tag=se&keywords=datacolor&qid=1728952077&sprefix=%2Caps%2C1670&sr=8-1", "Color “Surprises” Are a Thing of the Past: Datacolor’s exclusive DevicePreview TM Beta feature simulates what your photos can look like on other devices on your own calibrated screenCalibration for Today’s Digital Workflows: Spyder fully calibrates a wider than ever range of laptop/desktop displays, including OLED, mini-LED, and Apple Liquid Retina XDRFast & Easy Color Confidence: It only takes about 90 seconds to ensure an accurate color starting point for viewing and editingProfessional Results for Every Experience Level: Intuitive software and a pre-set calibration option make it easy for novices to get professional results while customizable calibration settings give professionals creative flexibilityAdaptable to Light Shifts: Ambient light sensor tracks/measures room light so you adjust your display brightness and contrast to ideal levels. The software can warn you to recalibrate or do it automatically using custom profiles based on light level."));
            productRepository.save(new Product(null, 1L, 1L, "Datacolor Spyder X2 Elite – Monitor Color Calibrator for Photographic, Video and Digital Design Work. Ensures Color Accuracy and Consistency for Monitors", 169.99, "/uploads/p_8.jpg", "𝗖𝗨𝗦𝗧𝗢𝗠 𝗖𝗔𝗟𝗜𝗕𝗥𝗔𝗧𝗜𝗢𝗡 𝗙𝗘𝗔𝗧𝗨𝗥𝗘𝗦: For users wanting more advanced calibration features, X2 Elite includes Video & Cinema Targets (Rec. 709, Rec. 2020); Advanced Display Mapping & Analysis; Unlimited Calibration Settings; Soft Proofing; and Studio Match for multiple monitor calibration.𝗡𝗘𝗪 𝗨𝗜: Spyder X2 Elite’s remastered software provides a cleaner more concise user interface (UI) experience.𝗙𝗢𝗥 𝗣𝗛𝗢𝗧𝗢 & 𝗩𝗜𝗗𝗘𝗢: Ideal for both photography and video work – perfect for hybrid photo/video work.𝗙𝗥𝗢𝗠 𝗡𝗢𝗩𝗜𝗖𝗘 𝗧𝗢 𝗣𝗥𝗢: X2 Elite’s features allow for a range of skill levels/needs – from expert-recommended calibration presets that help new users, to more advanced, customized color control that let you define your own parameters.𝗘𝗔𝗦𝗬 𝗖𝗢𝗡𝗡𝗘𝗖𝗧𝗜𝗢𝗡: Native USB-C connection. Also includes USB-A adaptor for added convenience.", "https://www.amazon.com/Datacolor-Spyder-Elite-Calibrador-consist%C3%AAncia/dp/B0C1LKYPTT/ref=sr_1_2?crid=QSZMZ31R2JK0&dib=eyJ2IjoiMSJ9.dbJ7edaztZQNOKIZSZkqoTjNIOpYEYSXtTULB9NssFM4MgjM0cH3-ORXtm97ij8Nrd2hsbAgsvACJSCp1LCosRCISPW48wlkjxxTboEbToEWXeCrl9kLMRVJhz-Av6IH0yRZSZHcJh8x14f3yuI4NQ3vmDWlPHHDPU-MTTofgaTE22aPm2o6ETza8mueMLBnaXj3tJltG-Wl2P77RP2j3NYlImkk5kVxTP6OX78JrpI.lUAGXe7srungbKVIHnys5_fIa2wNQh_76O6A0K_WJT4&dib_tag=se&keywords=datacolor&qid=1728952077&sprefix=%2Caps%2C1670&sr=8-2&th=1", "𝗖𝗨𝗦𝗧𝗢𝗠 𝗖𝗔𝗟𝗜𝗕𝗥𝗔𝗧𝗜𝗢𝗡 𝗙𝗘𝗔𝗧𝗨𝗥𝗘𝗦: For users wanting more advanced calibration features, X2 Elite includes Video & Cinema Targets (Rec. 709, Rec. 2020); Advanced Display Mapping & Analysis; Unlimited Calibration Settings; Soft Proofing; and Studio Match for multiple monitor calibration.𝗡𝗘𝗪 𝗨𝗜: Spyder X2 Elite’s remastered software provides a cleaner more concise user interface (UI) experience.𝗙𝗢𝗥 𝗣𝗛𝗢𝗧𝗢 & 𝗩𝗜𝗗𝗘𝗢: Ideal for both photography and video work – perfect for hybrid photo/video work.𝗙𝗥𝗢𝗠 𝗡𝗢𝗩𝗜𝗖𝗘 𝗧𝗢 𝗣𝗥𝗢: X2 Elite’s features allow for a range of skill levels/needs – from expert-recommended calibration presets that help new users, to more advanced, customized color control that let you define your own parameters.𝗘𝗔𝗦𝗬 𝗖𝗢𝗡𝗡𝗘𝗖𝗧𝗜𝗢𝗡: Native USB-C connection. Also includes USB-A adaptor for added convenience."));
            productRepository.save(new Product(null, 1L, 1L, "2024 Version Datacolor SpyderPro Monitor Calibration Tool: Ensures Accurate Color When Viewing and Editing Photos & Videos", 269.99, "/uploads/p_9.jpg", "Color “Surprises” Are a Thing of the Past: Datacolor’s exclusive DevicePreview TM Beta feature simulates what your photos can look like on other devices on your own calibrated screenCalibration for Today’s Digital Workflows: SpyderPro fully calibrates a wider than ever range of laptop/desktop displays, including high-brightness, OLED, mini-LED, and Apple Liquid Retina XDR, plus includes Rec. 709 and Rec. 2020 video targetsFast & Easy Color Confidence: It only takes about 90 seconds to ensure an accurate color starting point for viewing and editingColor Consistency Across Multiple Monitors: If the colors on all of your monitors aren’t consistent, neither is your visual story. The StudioMatch feature allows for side-by-side calibration for multiple computers/displaysProfessional Results for Every Experience Level: Intuitive software and a pre-set calibration option make it easy for novices to get professional results while customizable calibration settings give professionals creative flexibility", "https://www.amazon.com/Version-Datacolor-SpyderPro-Monitor-Calibration/dp/B0DDYHHRWF/ref=sr_1_3?crid=QSZMZ31R2JK0&dib=eyJ2IjoiMSJ9.dbJ7edaztZQNOKIZSZkqoTjNIOpYEYSXtTULB9NssFM4MgjM0cH3-ORXtm97ij8Nrd2hsbAgsvACJSCp1LCosRCISPW48wlkjxxTboEbToEWXeCrl9kLMRVJhz-Av6IH0yRZSZHcJh8x14f3yuI4NQ3vmDWlPHHDPU-MTTofgaTE22aPm2o6ETza8mueMLBnaXj3tJltG-Wl2P77RP2j3NYlImkk5kVxTP6OX78JrpI.lUAGXe7srungbKVIHnys5_fIa2wNQh_76O6A0K_WJT4&dib_tag=se&keywords=datacolor&qid=1728952077&sprefix=%2Caps%2C1670&sr=8-3", "Color “Surprises” Are a Thing of the Past: Datacolor’s exclusive DevicePreview TM Beta feature simulates what your photos can look like on other devices on your own calibrated screenCalibration for Today’s Digital Workflows: SpyderPro fully calibrates a wider than ever range of laptop/desktop displays, including high-brightness, OLED, mini-LED, and Apple Liquid Retina XDR, plus includes Rec. 709 and Rec. 2020 video targetsFast & Easy Color Confidence: It only takes about 90 seconds to ensure an accurate color starting point for viewing and editingColor Consistency Across Multiple Monitors: If the colors on all of your monitors aren’t consistent, neither is your visual story. The StudioMatch feature allows for side-by-side calibration for multiple computers/displaysProfessional Results for Every Experience Level: Intuitive software and a pre-set calibration option make it easy for novices to get professional results while customizable calibration settings give professionals creative flexibility"));
            productRepository.save(new Product(null, 1L, 1L, "Datacolor SpyderCHECKR 24 - Color calibrate your camera for consistent image color across multiple camera systems/lighting conditions. Target color chart has 24 target colors + grey card.", 49.99, "/uploads/p_10.jpg", "𝗖𝗢𝗟𝗢𝗥 𝗔𝗖𝗖𝗨𝗥𝗔𝗖𝗬, 𝗗𝗘𝗙𝗜𝗡𝗘𝗗 𝗗𝗘𝗧𝗔𝗜𝗟: Ensure accurate, consistent color from one shot to another, across different cameras, lenses, and lighting environments, plus capture every detail with optimal RAW process in- batch processing𝗕𝗨𝗗𝗚𝗘𝗧 𝗙𝗥𝗜𝗘𝗡𝗗𝗟𝗬, 𝗙𝗘𝗔𝗧𝗨𝗥𝗘-𝗥𝗜𝗖𝗛: It features 24 spectrally engineered color targets and a grey face target, all near/within the sRGB gamut to ensure compatibility with a wide range of devices. Perfect for visual comparisons and custom white balance adjustments.𝗔𝗟𝗜𝗚𝗡 𝗠𝗨𝗟𝗧𝗜𝗣𝗟𝗘 𝗖𝗔𝗠𝗘𝗥𝗔 𝗦𝗬𝗦𝗧𝗘𝗠𝗦: Easy alignment of multiple camera systems, including DSLR, Smartphones, drones and Action Cams - perfect for event photography.𝗦𝗧𝗥𝗘𝗔𝗠𝗟𝗜𝗡𝗘𝗗 𝗪𝗢𝗥𝗞𝗙𝗟𝗢𝗪: Taking a test photo with your Spyder Checkr 24 allows you to capture scene light color and intensity data, so you can color-calibrate your camera with software based HSL-presets, streamlining post-production workflow𝗢𝗡-𝗧𝗛𝗘-𝗚𝗢-𝗣𝗢𝗥𝗧𝗔𝗕𝗜𝗟𝗜𝗧𝗬: Its compact size and protective sleeve make Spyder Checkr 24 a take-with-you-everywhere photo tool – perfect for location shoots, changing light conditions, and multiple camera and lens usage – wherever your camera takes you!", "https://www.amazon.com/Datacolor-SCK200-SpyderCHECKR-24/dp/B00LPS46TW/ref=sr_1_5?crid=QSZMZ31R2JK0&dib=eyJ2IjoiMSJ9.dbJ7edaztZQNOKIZSZkqoTjNIOpYEYSXtTULB9NssFM4MgjM0cH3-ORXtm97ij8Nrd2hsbAgsvACJSCp1LCosRCISPW48wlkjxxTboEbToEWXeCrl9kLMRVJhz-Av6IH0yRZSZHcJh8x14f3yuI4NQ3vmDWlPHHDPU-MTTofgaTE22aPm2o6ETza8mueMLBnaXj3tJltG-Wl2P77RP2j3NYlImkk5kVxTP6OX78JrpI.lUAGXe7srungbKVIHnys5_fIa2wNQh_76O6A0K_WJT4&dib_tag=se&keywords=datacolor&qid=1728952077&sprefix=%2Caps%2C1670&sr=8-5&th=1", "𝗖𝗢𝗟𝗢𝗥 𝗔𝗖𝗖𝗨𝗥𝗔𝗖𝗬, 𝗗𝗘𝗙𝗜𝗡𝗘𝗗 𝗗𝗘𝗧𝗔𝗜𝗟: Ensure accurate, consistent color from one shot to another, across different cameras, lenses, and lighting environments, plus capture every detail with optimal RAW process in- batch processing𝗕𝗨𝗗𝗚𝗘𝗧 𝗙𝗥𝗜𝗘𝗡𝗗𝗟𝗬, 𝗙𝗘𝗔𝗧𝗨𝗥𝗘-𝗥𝗜𝗖𝗛: It features 24 spectrally engineered color targets and a grey face target, all near/within the sRGB gamut to ensure compatibility with a wide range of devices. Perfect for visual comparisons and custom white balance adjustments.𝗔𝗟𝗜𝗚𝗡 𝗠𝗨𝗟𝗧𝗜𝗣𝗟𝗘 𝗖𝗔𝗠𝗘𝗥𝗔 𝗦𝗬𝗦𝗧𝗘𝗠𝗦: Easy alignment of multiple camera systems, including DSLR, Smartphones, drones and Action Cams - perfect for event photography.𝗦𝗧𝗥𝗘𝗔𝗠𝗟𝗜𝗡𝗘𝗗 𝗪𝗢𝗥𝗞𝗙𝗟𝗢𝗪: Taking a test photo with your Spyder Checkr 24 allows you to capture scene light color and intensity data, so you can color-calibrate your camera with software based HSL-presets, streamlining post-production workflow𝗢𝗡-𝗧𝗛𝗘-𝗚𝗢-𝗣𝗢𝗥𝗧𝗔𝗕𝗜𝗟𝗜𝗧𝗬: Its compact size and protective sleeve make Spyder Checkr 24 a take-with-you-everywhere photo tool – perfect for location shoots, changing light conditions, and multiple camera and lens usage – wherever your camera takes you!"));
            productRepository.save(new Product(null, 1L, 1L, "Datacolor Spyder Checkr Video – Video Color Tool with Patent-Pending Color Pattern Card That leverages How Video is Processed, for More Color Information at-a-Glance for Precise Color & Exposure.", 129.99, "/uploads/p_11.jpg", "𝗔𝗗𝗩𝗔𝗡𝗖𝗘𝗗 𝗖𝗢𝗟𝗢𝗥 𝗔𝗖𝗖𝗨𝗥𝗔𝗖𝗬: An advanced color reference tool for video that works with vector scopes to help ensure color accuracy and consistency across a range of cameras and lens combinations from the start of shooting to simplify and facilitate post-production color correction.𝗣𝗔𝗧𝗘𝗡𝗧-𝗣𝗘𝗡𝗗𝗜𝗡𝗚 𝗗𝗘𝗦𝗜𝗚𝗡: Spyder Checkr Video is the all-in-one color chart to optimize and harmonize any video workflow. Its patent-pending Color Pattern Card design is our most comprehensive color target to date, generating a pattern of hues at two saturation levels, allowing you to see how your colors are being captured throughout all of the hues between primary and secondary colors.𝗠𝗨𝗟𝗧𝗜-𝗙𝗨𝗡𝗖𝗧𝗜𝗢𝗡𝗔𝗟 𝗧𝗔𝗥𝗚𝗘𝗧 𝗖𝗔𝗥𝗗𝗦: Includes 5 high-gloss target cards - 2 types of color cards for different workflows, (a conventional color patch card + our patent-pending Color Pattern Card), a gradient greyscale card, a solid, neutral grey card for white balance and a focus star card. Glossy cards allow for high color saturation, wider color gamut, plus easier flare identification.𝗖𝗢𝗠𝗣𝗔𝗧𝗜𝗕𝗟𝗘 𝗪𝗜𝗧𝗛 𝗦𝗣𝗬𝗗𝗘𝗥 𝗖𝗛𝗘𝗖𝗞𝗥 𝗣𝗛𝗢𝗧𝗢: Spyder Checkr Video Cards are replaceable and interchangeable with Spyder Checkr Photo cards for photo/video hybrid work, allowing for greater cost and sustainability efficiencies.𝗘𝗥𝗚𝗢𝗡𝗢𝗠𝗜𝗖 𝗛𝗔𝗡𝗗𝗛𝗘𝗟𝗗 𝗗𝗘𝗦𝗜𝗚𝗡: Ergonomic case design fits comfortably and securely in the hand, minimizing fatigue and handling errors.", "https://www.amazon.com/Datacolor-Spyder-Checkr-Video-Glance/dp/B0CHH626W2/ref=sr_1_8?crid=QSZMZ31R2JK0&dib=eyJ2IjoiMSJ9.dbJ7edaztZQNOKIZSZkqoTjNIOpYEYSXtTULB9NssFM4MgjM0cH3-ORXtm97ij8Nrd2hsbAgsvACJSCp1LCosRCISPW48wlkjxxTboEbToEWXeCrl9kLMRVJhz-Av6IH0yRZSZHcJh8x14f3yuI4NQ3vmDWlPHHDPU-MTTofgaTE22aPm2o6ETza8mueMLBnaXj3tJltG-Wl2P77RP2j3NYlImkk5kVxTP6OX78JrpI.lUAGXe7srungbKVIHnys5_fIa2wNQh_76O6A0K_WJT4&dib_tag=se&keywords=datacolor&qid=1728952077&sprefix=%2Caps%2C1670&sr=8-8", ""));

            System.out.println("Initial Product data loaded into SQLite");
        } else {
            System.out.println("Product data already exists, skipping initialization.");
        }
        
        if(organizationRepository.count() == 0) {
            // Create a list of sample organizations
            List<Organization> organizations = new ArrayList<>();

            // Level 1 (Root)
            Organization org1 = Organization.builder()
                    .name("Global Corp")
                    .address("Global Headquarters")
                    .description("The top-level organization")
                    .parentId(null)  // Root organization
                    .build();
            organizations.add(org1);

            // Level 2 (Divisions)
            Organization org2 = Organization.builder()
                    .name("North America Division")
                    .address("USA, New York")
                    .description("Regional division for North America")
                    .parentId(1L) // Parent is Global Corp
                    .build();

            Organization org3 = Organization.builder()
                    .name("Europe Division")
                    .address("Germany, Berlin")
                    .description("Regional division for Europe")
                    .parentId(1L)
                    .build();

            Organization org4 = Organization.builder()
                    .name("Asia-Pacific Division")
                    .address("China, Shanghai")
                    .description("Regional division for Asia-Pacific")
                    .parentId(1L)
                    .build();

            Organization org5 = Organization.builder()
                    .name("South America Division")
                    .address("Brazil, São Paulo")
                    .description("Regional division for South America")
                    .parentId(1L)
                    .build();

            organizations.add(org2);
            organizations.add(org3);
            organizations.add(org4);
            organizations.add(org5);

            // North America Division (Level 3 - Departments)
            Organization org6 = Organization.builder()
                    .name("USA Operations")
                    .address("USA, San Francisco")
                    .description("Operations in the United States")
                    .parentId(2L) // Parent is North America Division
                    .build();

            Organization org7 = Organization.builder()
                    .name("Canada Operations")
                    .address("Canada, Toronto")
                    .description("Operations in Canada")
                    .parentId(2L)
                    .build();

            organizations.add(org6);
            organizations.add(org7);

            // Europe Division (Level 3 - Departments)
            Organization org8 = Organization.builder()
                    .name("Germany Operations")
                    .address("Germany, Munich")
                    .description("Operations in Germany")
                    .parentId(3L) // Parent is Europe Division
                    .build();

            Organization org9 = Organization.builder()
                    .name("UK Operations")
                    .address("UK, London")
                    .description("Operations in the UK")
                    .parentId(3L)
                    .build();

            Organization org10 = Organization.builder()
                    .name("France Operations")
                    .address("France, Paris")
                    .description("Operations in France")
                    .parentId(3L)
                    .build();

            organizations.add(org8);
            organizations.add(org9);
            organizations.add(org10);

            // Asia-Pacific Division (Level 3 - Departments)
            Organization org11 = Organization.builder()
                    .name("China Operations")
                    .address("China, Beijing")
                    .description("Operations in China")
                    .parentId(4L) // Parent is Asia-Pacific Division
                    .build();

            Organization org12 = Organization.builder()
                    .name("Japan Operations")
                    .address("Japan, Tokyo")
                    .description("Operations in Japan")
                    .parentId(4L)
                    .build();

            Organization org13 = Organization.builder()
                    .name("Australia Operations")
                    .address("Australia, Sydney")
                    .description("Operations in Australia")
                    .parentId(4L)
                    .build();

            organizations.add(org11);
            organizations.add(org12);
            organizations.add(org13);

            // South America Division (Level 3 - Departments)
            Organization org14 = Organization.builder()
                    .name("Brazil Operations")
                    .address("Brazil, Rio de Janeiro")
                    .description("Operations in Brazil")
                    .parentId(5L) // Parent is South America Division
                    .build();

            Organization org15 = Organization.builder()
                    .name("Argentina Operations")
                    .address("Argentina, Buenos Aires")
                    .description("Operations in Argentina")
                    .parentId(5L)
                    .build();

            organizations.add(org14);
            organizations.add(org15);

            // Level 4 - Sub-departments under USA Operations (North America)
            Organization org16 = Organization.builder()
                    .name("California Branch")
                    .address("USA, Los Angeles")
                    .description("Handles operations in California")
                    .parentId(6L) // Parent is USA Operations
                    .build();

            Organization org17 = Organization.builder()
                    .name("New York Branch")
                    .address("USA, New York City")
                    .description("Handles operations in New York")
                    .parentId(6L)
                    .build();

            organizations.add(org16);
            organizations.add(org17);

            // Level 4 - Sub-departments under Germany Operations (Europe)
            Organization org18 = Organization.builder()
                    .name("Berlin Office")
                    .address("Germany, Berlin")
                    .description("Operations in Berlin")
                    .parentId(8L) // Parent is Germany Operations
                    .build();

            Organization org19 = Organization.builder()
                    .name("Munich Office")
                    .address("Germany, Munich")
                    .description("Operations in Munich")
                    .parentId(8L)
                    .build();

            organizations.add(org18);
            organizations.add(org19);

            // Level 5 - Teams under California Branch (North America)
            Organization org20 = Organization.builder()
                    .name("California Sales Team")
                    .address("USA, Los Angeles")
                    .description("Sales team for California")
                    .parentId(16L) // Parent is California Branch
                    .build();

            Organization org21 = Organization.builder()
                    .name("California Marketing Team")
                    .address("USA, San Diego")
                    .description("Marketing team for California")
                    .parentId(16L)
                    .build();

            organizations.add(org20);
            organizations.add(org21);

            // Level 6 - Sub-teams under California Sales Team (North America)
            Organization org22 = Organization.builder()
                    .name("West Coast Sales")
                    .address("USA, San Francisco")
                    .description("Sales team for the west coast")
                    .parentId(20L) // Parent is California Sales Team
                    .build();

            Organization org23 = Organization.builder()
                    .name("East Coast Sales")
                    .address("USA, Los Angeles")
                    .description("Sales team for the east coast")
                    .parentId(20L)
                    .build();

            organizations.add(org22);
            organizations.add(org23);

            // Save all organizations in bulk
            organizationRepository.saveAll(organizations);

            System.out.println("Initial Organization data loaded into SQLite");
        } else {
            System.out.println("Organization data already exists, skipping initialization.");
        }
    }
}
