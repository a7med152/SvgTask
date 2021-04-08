package com.example.android_task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.widget.ImageView;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


public class MainActivity extends AppCompatActivity {

    ArrayList<String> stringArrayList;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stringArrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.svgRecyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(stringArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);


        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(this.getAssets().open("MapTest.svg"));


            NodeList nodes = document.getElementById("DOORS").getChildNodes();
            for (int i = 0; i < document.getElementById("DOORS").getChildNodes().getLength(); i++) {
                if (document.getElementById("DOORS").getChildNodes().item(i).getNodeName().equals("line")){
                    Element element = (Element) nodes.item(i);
                    stringArrayList.add(element.getAttribute("id"));
                }
            }

            recyclerViewAdapter.notifyDataSetChanged();


        } catch (IOException e) {
            Log.e("IOException",e.toString());
            e.printStackTrace();
        } catch (SAXException e) {
            Log.e("SAXException",e.toString());
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            Log.e("ParserException",e.toString());
            e.printStackTrace();
        }

    }
}