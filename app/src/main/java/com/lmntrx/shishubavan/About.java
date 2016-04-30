package com.lmntrx.shishubavan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
        versionElement.setTitle("Version 1.0");

        Element name = new Element();
        name.setTitle("Janaseva");

        Element lmntrx = new Element();
        lmntrx.setTitle("Powered By LmntrX");
        lmntrx.setIntent(browserIntent);

        String desc="Commenced in the year 1999, we, Janaseva Sisubhavan has initiated in this arena to eradicate the problem pertaining to child exploitation. Our sole vision is to eradicate child exploitation from the society and there by CREATE AN INDIA WITHOUT STREET CHILDREN.\n" +
                "\n" +
                "Child exploitation is one of the biggest problems of the planet. It is increasing constantly. The worst part is that they must defend their life every day to face up the violence of all forms physical, verbal or psychological. The street children include those who live on the streets of a city.\n" +
                "\n" +
                "Mr. José Maveli, the Founder and the Chairman of Janaseva Sisubhavan, sole vision is to offer a stable home or shelter for the street children. Children of the street lack emotional and psychological support of a family, and it’s our responsibility to give them a house. Janaseva Sisubhavan raises street children’s voices, promotes their rights and improves their lives. We are living adequate for the child's physical, mental, spiritual, moral and social development. The hidden and isolated nature of street children makes it difficult to gather the exact figure. Our team is working hard to make their life better.\n" +
                "\n" +
                "One important aspect that requires being taken care of is child abuse. We strive hard to make them self-supporting, confident, and well-mannered individuals contributing their best to the society.\n" +
                "\n" +
                "Janaseva Girl’s Home is located at Aluva near U.C. College. There are 300 children living with utmost security and comfort under the safe wings of Janaseva Sisubhavan. In 2007 Janaseva Sisubhavan also built a Boys home at Nedumbassery. The building is situated at 61/2 acres of land, and is a 30,000 sq.ft. These buildings are equipped with all the modern amenities and international fixtures, and can accommodate approximately 300 children. It consists of primary classes up to 4th standard.\n" +
                "\n" +
                "In 2008, Janaseva Sisubhavan started its own sports academy for the entire development of these destitute children. Our prime motive is to give better coaching to our children, and hence molding them as the proud citizen of India. The children of Sisubhavan have proved their excellence not only in studies but in other extracurricular activities especially sports, drawing, stage show etc… also. They scored a remarkable place in state and district school level football team.";





        Element address = new Element();
        address.setTitle("Address:\nV Janaseva Sisubhavan\n" +
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
                .setDescription(desc)
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
