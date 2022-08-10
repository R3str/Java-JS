package program.util;

import java.util.Random;

public class NameGenerator
{
    private static String[] names =
            {
                "paint","qwerty","NET_Paint","Visual_Studio","VS_Code","GTA_V","CHROME","Security_Data","Subnautica",
                "Not_Security_Data","Variant_11","Watch_Dogs","RDR","Raid:Shadow_Legends","Project_Zomboid","SAMP","Java_FX",
                "RAID:SHADOW_LEGENDS","Opera","Photoshop","Steam","EGS","Ubisoft_launcher","Intellij_IDEA","OBS_Studio","virus",
                "Bethesda.net_launcher","Discrod","Git"
            };

    private static Random random = new Random();

    public static String generate()
    {
        String result = "";

        result += names[random.nextInt(names.length)];

        result += ".exe";

        return result;
    }
}

