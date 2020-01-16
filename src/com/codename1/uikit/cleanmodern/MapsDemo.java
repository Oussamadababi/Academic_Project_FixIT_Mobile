/*
 * Copyright (c) 2012, Codename One and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Codename One designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *  
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 * 
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Please contact Codename One through http://www.codenameone.com/ if you 
 * need additional information or have any questions.
 */
package com.codename1.uikit.cleanmodern;

import Entites.User;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import static com.codename1.util.StringUtil.tokenizeString;


import java.io.IOException;
import java.util.StringTokenizer;




/**
 * This is a simple demo that demonstrates how to use the MapComponent and how
 * to show POI on a map using google location API's Make sure to get a key from
 * https://developers.google.com/maps/documentation/places/ to run the 'Find
 * Resturants' sub demo
 *
 * IMPORTANT - This demo uses the simple tiling map component to display a Map.
 * In order to display a Native Map on the device please refer to this:
 * http://www.codenameone.com/blog/mapping-natively.html
 *
 *
 * @author Chen
 */
public class MapsDemo extends Form {
 public static String lat;
    public static String lon;
    private Coord lastLocation;

//    String u;
//
//    public void setUser(String u) {
//
//        this.u = u;
//
//    }
    
    public void init(Object context) {
  //     Resources theme = UIManager.initFirstTheme("/theme_1");
//        //Enable Toolbar to all Forms by default
        Toolbar.setGlobalToolbar(false);
        // Pro only feature, uncomment if you have a pro subscription
        //Log.bindCrashProtection(true);

    }

    public void start() {

        Form map = new Form();
        Form formb = new Form();
        

        map.setLayout(new BorderLayout());
        final MapComponent mc = new MapComponent();

        putMeOnMap(mc);
        mc.zoomToLayers();
        mc.zoomTo(lastLocation, 7);

        map.addComponent(BorderLayout.CENTER, mc);

        Button backb = new Button("back");
        Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container C4 = new Container(new BoxLayout(BoxLayout.X_AXIS));

//        backb.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                DisplayMore aa = new DisplayMore(res, u);
//                aa.showBack();
//            }
//        });
        map.setHeight(MOVE_CURSOR);
        C4.add(map);
        C3.add(backb);
        formb.add(C3);
        formb.add(C4);

        formb.show();
        
    }


    private void putMeOnMap(MapComponent map) {

        try {

            StringTokenizer st = new StringTokenizer(AnnonceForm.loc, ",");
            st.toString();
            int z = 0;
            while (st.hasMoreTokens()) {
                z++;
                if (z == 1) {
                    lat = st.nextToken();
                } else {
                    lon = st.nextToken();
                }
            }

            System.out.println("lat" + lat);
            System.out.println("lon" + lon);

            Location loc = LocationManager.getLocationManager().getCurrentLocation();
            lastLocation = new Coord(36.7997069, 10.167524500000013);
            Image i = Image.createImage("/blue_pin.png");
            PointsLayer pl = new PointsLayer();
            pl.setPointIcon(i);

            PointLayer p = new PointLayer(lastLocation, "TM", i);

            Float latf = Float.valueOf(lat);
            Float lonf = Float.valueOf(lon);

            p.setLatitude(latf);
            p.setLongitude(lonf);

            p.setDisplayName(true);
            pl.addPoint(p);
            pl.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evt) {
                    PointLayer p = (PointLayer) evt.getSource();
                    System.out.println("pressed " + p);

                    Dialog.show("Details", "Tu es là!" + "\n" + 36.8479557 + "|" + 10.2767195, "Ok", null);
                }
            });
            map.addLayer(pl);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void stop() {
        System.out.println("stopped");
    }

    public void destroy() {
        System.out.println("destroyed");

    }


    
    
}
