package com.group2.mhci.seefood;

import java.util.ArrayList;

/**
 * Created by Brian on 4/3/2018.
 */

public class food {
    public String TH;
    public String EN;
    public String Roman;
    public String desc;
    public Integer[] images;

    public food(String TH, String EN, String Roman, String desc, Integer[] images) {
        this.TH = TH;
        this.EN = EN;
        this.Roman = Roman;
        this.desc = desc;
        this.images = images;
    }
    private static food mangoRice = new food(
            "ข้าวเหนียวมะม่วง",
            "Mango with Sticky Rice",
            "Khaoniao Mamuang",
            "Sweet sticky rice served with mango.\n" +
                    "\n" +
                    "Mango sticky rice is a traditional Thai dessert made with glutinous rice, fresh mango and coconut milk, and eaten with a fork, spoon, or sometimes the hands. Although originating in Thailand, it is consumed throughout the Indochina region of Southeast Asia, including Laos, Cambodia, and Vietnam. Mango sticky rice is usually eaten in the peak mango season, the summer months of April and May in Thailand. This text has been taken from Wikipedia for prove-of-concept purpose.",
            new Integer[]{R.drawable.mango_sticky_rice_1, R.drawable.mango_sticky_rice_2, R.drawable.mango_sticky_rice_3}
    );
    private static food chaMongKut = new food(
            "จ่ามงกุฎ",
            "Cha Mongkut",
            "Cha Mongkut",
            "Sweet made of flour, coconut milk, and sugar.\n" +
                    "\n" +
                    "Cha mongkut is one of the traditional Thai desserts. It is made of rice flour and glutinous flour mixed with green bean flour, and is stirred with coconut milk and sugar until it becomes sticky; it is typically sprinkled with chopped roasted peanuts on top or stuffed with melon seeds. Traditionally, they are cut it into bite-size pieces and wrapped with banana leaf. Moreover, the aromatic scents of the dessert are given by fresh flowers such as Kesidang, Ylang-Ylang, Damask rose, and Jasmine with boiled water, which is used to squeeze coconut milk. This text is taken from Wikipedia for prove-of-concept purpose.",
            new Integer[]{R.drawable.cha_mongkut_1, R.drawable.cha_mongkut_2, R.drawable.cha_mongkut_3}
    );
    private static food riceChicken = new food(
            "ข้าวมันไก่",
            "Hainanese Chicken Rice",
            "Khao Man Gai",
            "\uD83C\uDF56 Typically contain chicken\n" +
                    "\uD83C\uDF36 Sauce may be spicy.\n" +
                    "\n" +
                    "Rice cooked in garlic oil, served with boiled chicken and soup.\n" +
                    "\n" +
                    "Hainanese chicken rice is a dish adapted from early Chinese immigrants originally from Hainan province in southern China, extremely popular in South East Asia. The Thai's variant of this dish is known as Khao Mun Gai, literally rice with chicken fat. Part of this text is taken from Wikipedia for prove-of-concept purpose.",
            new Integer[]{R.drawable.khao_man_gai_1, R.drawable.khao_man_gai_2, R.drawable.khao_man_gai_3}
    );
    private static food greenCurry = new food(
            "แกงเขียวหวาน",
            "Green Curry",
            "Kaeng Khiao Wan",
            "\uD83C\uDF56 Typically contain meat\n" +
                    "\uD83C\uDF36 Typically spicy\n" +
                    "\n" +
                    "Thai curry made of green chillies and coconut wilk, typically eaten with rice or round rice noodle.\n" +
                    "\n" +
                    "The ingredients typically includes coconut milk, green curry paste, palm sugar and fish sauce. The main protein of the dish could be fish, fish ball, or meat. The dish is typically served with rice or rounded noodles knwon as Khanom Chin as a single dish. Otherwise, the thicker version may also be served with Roti. Part of this text is adapted from Wikipedia for prove-of-concept purpose.",
            new Integer[]{R.drawable.green_curry_1, R.drawable.green_curry_2, R.drawable.green_curry_3}
    );
    private static food padThai = new food(
            "ผัดไทย",
            "Pad Thai",
            "Pad Thai",
            "\uD83C\uDF56 Typically contain meat\n" +
                    "\uD83C\uDF36 Typically spicy\n" +
                    "\uD83E\uDD5C Typically contain peanut\n" +
                    "\n" +
                    "Stir-fried rice noodle with egg, tofu, and shrimp.\n" +
                    "\n" +
                    "Pad thai is a stir-fried rice noodle dish commonly served as a street food and at casual local eateries in Thailand. It is made with soaked dried rice noodles, which are stir-fried with eggs and chopped firm tofu, and is flavored with tamarind pulp, fish sauce, dried shrimp, garlic or shallots, red chili pepper and palm sugar and served with lime wedges and often chopped roasted peanuts. It may contain other vegetables like bean sprouts, garlic chives, pickled radishes or turnips, and raw banana flowers. It may also contain fresh shrimp, crab, squid, chicken or other animal products. Many of the ingredients are provided on the side as condiments such as the red chili pepper, lime wedges, roasted peanuts, bean sprouts and other miscellaneous fresh vegetables. Vegetarian versions may substitute soy sauce for the fish sauce and omit the shrimp. This text is taken from Wikipedia for prove-of-concept purpose.",
            new Integer[]{R.drawable.pad_thai_1, R.drawable.pad_thai_2, R.drawable.pad_thai_3}
    );
    private static food udon = new food(
            "อุด้ง",
            "Udon",
            "Udon",
            "Udon (Japanese: うどん) is a type of thick wheat flour noodle, similar to Italian pasta, but much thicker.\n" +
                    "\n" +
                    "It is often served hot as a noodle soup in its simplest form in a mildly flavoured broth made of dashi, soy sauce, and mirin. It is usually topped with thinly chopped scallions. Other common toppings include tempura or aburaage, a type of deep-fried tofu pockets seasoned with sugar, mirin, and soy sauce. A thin slice of kamaboko, a halfmoon-shaped fish cake, is often added. Shichimi can be added to taste. This text is taken from Wikipedia for prove-of-concept purpose.",
            new Integer[]{R.drawable.udon_1, R.drawable.udon_2, R.drawable.udon_3}
    );
    private static food gyuudon = new food(
            "ข้าวหน้าเนื้อ(กิวด้ง)",
            "Beef bowl",
            "Khao Na Neua / Gyuudon",
            "\uD83C\uDF56 Contain meat\n" +
                    "\n" +
                    "Beef bowl (Japanese: 牛丼 / Gyuudon), a bowl of rice topped with beef and onion.\n" +
                    "\n" +
                    "Gyūdon, literally beef bowl, is a Japanese dish consisting of a bowl of rice topped with beef and onion simmered in a mildly sweet sauce flavored with dashi (fish and seaweed stock), soy sauce and mirin (sweet rice wine). It also often includes shirataki noodles, and is sometimes topped with a raw egg or a soft poached egg (onsen tamago). A very popular food in Japan, it is commonly served with beni shōga (pickled ginger), shichimi (ground chili pepper), and a side dish of miso soup. This text is taken from Wikipedia for prove-of-concept purpose.",
            new Integer[]{R.drawable.gyuudon_1, R.drawable.gyuudon_2, R.drawable.gyuudon_3}
    );

    public static food[][] foodList = {
            {mangoRice, chaMongKut},
            {riceChicken, greenCurry, gyuudon},
            {padThai, udon},
    };

    public static Integer[] categoryThumb = {
            R.drawable.dessert,R.drawable.rice,R.drawable.noodles
    };

    public static String[] categoryNames = {"Desserts","Rice", "Noodles"};
}
