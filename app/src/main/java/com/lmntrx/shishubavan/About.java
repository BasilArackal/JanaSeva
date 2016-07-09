package com.lmntrx.shishubavan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Element websiteElement = new Element();
        String url = "http://lmntrx.com/";
        websiteElement.setValue(url);

        Uri uri = Uri.parse(url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);



        Element versionElement = new Element();
        versionElement.setTitle(getResources().getString(R.string.app_version));

        Element name = new Element();
        name.setTitle("Janaseva Congress:Kerala Helplines");

        Element lmntrx = new Element();
        lmntrx.setTitle("Powered By LmntrX");
        lmntrx.setIntent(browserIntent);

        String new_desc="Janaseva Sisubhavan has initiated in the arena of social service in 1999 to eradicate the problem pertaining to child exploitation. Our sole vision is to eradicate child exploitation from the society and there by CREATE AN INDIA WITHOUT STREET CHILDREN.\n" +
                "\n" +
                "Child exploitation is one of the biggest problems of the planet. It is increasing constantly. The worst part is that they must defend their life every day to face up the violence of all forms physical, verbal or psychological. The street children include those who live on the streets of a city.\n" +
                "\n" +
                "Mr. José Maveli, the Founder and the Chairman of Janaseva Sisubhavan, has the sole vision to offer a stable home or shelter for the street children. Children of the street lack emotional and psychological support of a family, and it’s our responsibility to give them a house. Janaseva Sisubhavan raises street children’s voices, promotes their rights and improves their lives. We are living adequate for the child's physical, mental, spiritual, moral and social development. The hidden and isolated nature of street children makes it difficult to gather the exact figure. Our team is working hard to make their life better.\n" +
                "\n" +
                "Now we have launched a mobile application ALL-IN-ONE HELPLINE to avail our service where ever required. Any crime in the area of  Drug Abuse, Child Molests, Woman Torture, Stray Dog Attack, Child Labour and Begging, Waste Management issues etc,  if reported immediate rescue measures will reach the spot. \n" +
                "\n" +
                "\n" +
                "Jose Mavely \n" +
                "Chairman\n" +
                "Janaseva Sisubhavan";




        Element address = new Element();
        address.setTitle("Address:\nJanaseva Sisubhavan\n" +
                "U.C. College P.O, Aluva\n" +
                "Kerala");


        Element pin = new Element();
        pin.setTitle("Pin: 683101");

        Element mobile = new Element();
        mobile.setTitle("Mobile :\n+91 98954 66339 \n" +
                "+91 96333 61101");

        Element whatsapp = new Element();
        whatsapp.setTitle("Whatsapp Number:+ 91 96333 61103");

        Element mail = new Element();
        mail.setTitle("sisubhavan@gmail.com");


        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.newmaveli)
                .setDescription(new_desc)
                .addGroup("Reach Us")
                .addItem(address)
                .addItem(pin)
                .addItem(mobile)
                .addItem(whatsapp)
                .addItem(mail)
                .addGroup("Connect with us")
                .addEmail("sisubhavan@gmail.com")
                .addWebsite("http://www.janasevasisubhavan.net/")
                .addFacebook("janasevacongress")
                .addYoutube("UCs-6lCl0gkq7jQsIw9q_W-g")
                .addGroup("App Details")
                .addItem(name)
                .addItem(versionElement)
                .addItem(lmntrx)
                .create();

        setContentView(aboutPage);


    }

}
