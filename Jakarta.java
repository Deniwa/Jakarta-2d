import processing.core.PApplet;
import processing.core.PFont;

public class Jakarta extends PApplet {

    Lightning lightning;
    int lastLightningTime;
    Hujan[] hujans;
    Hujan1[] hujans1;
    Awan[] awans;
    int numHujans = 100;
    int numAwans = 5;
    Laut sea = new Laut();
    Monas tugu = new Monas();
    Bangunan bangunan = new Bangunan();
    JakartaText jakartaText = new JakartaText();
    Bayangan shadow = new Bayangan();
    Penggaris ruler = new Penggaris();
    Bulan moon = new Bulan();
    BintangKelapKelip[] bintangKelapKelips;
    int numBintangKelapKelips = 60;
    Sekoci boat = new Sekoci();

    public static void main(String[] args) {
        Jakarta mySketch = new Jakarta();
        PApplet.runSketch(new String[] {"Hello Processing"}, mySketch);
    }

    public void settings() {
//        size(1920, 1080);
        fullScreen();
    }

    public void setup() {
        //Hujan
        hujans = new Hujan[numHujans];
        for (int i = 0; i < numHujans; i++) {
            hujans[i] = new Hujan();
        }
        hujans1 = new Hujan1[numHujans];
        for (int i = 0; i < numHujans; i++) {
            hujans1[i] = new Hujan1();
        }

        //Awan
        awans = new Awan[numAwans];
        for (int i = 0; i < numAwans; i++) {
            awans[i] = new Awan();
        }

        //Petir
        lightning = new Lightning();
        lastLightningTime = millis();

        //Bintang
        bintangKelapKelips = new BintangKelapKelip[numBintangKelapKelips];
        for (int i = 0; i < numBintangKelapKelips; i++) {
            float x = random(width);
            float y = random(50, 700);
            int duration = (int) random(5000, 6000);
            bintangKelapKelips[i] = new BintangKelapKelip(x, y, duration);
        }
    }

    public void draw() {
        stroke(0, 0, 0);
        strokeWeight(0);
        background(22, 29, 41);
        moon.draw();
        for (Hujan1 hujan : hujans1) {
            hujan.fall();
            hujan.display();
        }
        for (BintangKelapKelip bintangKelapKelip : bintangKelapKelips) {
            bintangKelapKelip.update();
            bintangKelapKelip.display();
        }
        jakartaText.display();
        //Gerak Awan
        for (Awan awan : awans) {
            awan.move();
            awan.display();
        }
        //Petir
        if (millis() - lastLightningTime > 5000) {
            lightning.display();
            lastLightningTime = millis();
        }
        sea.draw();
        bangunan.draw();
        tugu.draw();
        shadow.draw();
        ruler.draw();
        //Tetes Hujan
        for (Hujan hujan : hujans) {
            hujan.fall();
            hujan.display();
        }
        boat.draw();
        boat.move(0.1f);
        if (boat.x < -150) {
            boat = new Sekoci();
        }
    }

    class Hujan {
        float x, y, speed;

        Hujan() {
            x = random(width);
            y = random(height);
            speed = random(5, 10);
        }

        void fall() {
            y += speed;
            if (y > height) {
                y = 0;
                x = random(width);
            }
        }

        void display() {
            stroke(155, 181, 199);
            line(x, y, x, y + 20);
        }
    }

    class Hujan1 {
        float x, y, speed;

        Hujan1() {
            x = random(width);
            y = random(height);
            speed = random(5, 10);
        }

        void fall() {
            y += speed;
            if (y > height) {
                y = 0;
                x = random(width);
            }
        }

        void display() {
            stroke(155, 181, 199);
            line(x, y, x, y + 75);
        }
    }

    class Penggaris {
        void draw() {
//            fill(0,0,0);
//            rect(734, 0,5, 1080);
//            fill(0,0,0);
//            line(734,720, 1150, 380);
        }
    }

    class Laut {
        void draw() {
            
            fill(30, 48, 105);
            rect(0, 720, 1920, 250);
            
            int gradationColor = color(18, 108, 204);
            int baseColor = color(5, 59, 117);
            gradientRect(0, 720, 1920, 250, baseColor, gradationColor);
            
            fill(222, 222, 222);
            rect(200, 800, 150, 1);
            rect(520, 873, 124, 1);
            rect(123, 790, 200, 1);
            rect(1100, 900, 120, 1);
            rect(1248, 823, 172, 1);
            rect(width / 2 + 100, 740, 90, 1);
            rect(690, 790, 100, 1);
            rect(720, 798, 30, 1);
        }

        void gradientRect(float x, float y, float w, float h, int c1, int c2) {
            noFill();
            for (int i = (int) y; i <= y + h; i++) {
                float inter = map(i, y, y + h, 0, 1);
                int c = lerpColor(c1, c2, inter);
                stroke(c);
                line(x, i, x + w, i);
            }
        }
    }

    class Bulan {
        void draw() {
            fill(255, 255, 255);
            circle(width/2, 100, 100);
            fill(242, 241, 177, 25);
            circle(width/2,100, 150 );
        }
    }

    class Monas {
        void draw() {
            fill(225, 241, 245);
            rect(700, 280 + 300, 75, 200, 10, 10, 10, 10);
            fill(165, 172, 176);
            rect(680, 575, 115, 25);
            rect(680, 605, 115, 30);
            fill(12, 12, 13);
            rect(695, 600, 85, 5);
            fill(212, 168, 57);
            triangle(700, 575, 737, 500, 775, 575 );
            fill(212, 168, 57);
            rect(700, 560, 75,25);
        }
    }

    class Bangunan {
        void draw() {
            // Bangunan 2
            fill(166, 180, 189);
            rect(1100, 420, 200, 315);
            fill(194, 200, 204);

            //Bolongan anjir bingung pak
            fill(22, 29, 41);
            rect(1110, 430, 180, 70);
            fill(56, 62, 71);
            quad(1310, 430, 1310, 495, 1346, 465, 1346, 400);
            fill(166, 180, 189);
            quad(1350, 460, 1305, 500, 1100, 500, 1150,460);

            //Lanjutan bangunan 2
            fill(191, 196, 199);
            quad(1150, 380, 1350, 380, 1300, 420, 1100, 420);
            fill(122, 130, 135);
            quad(1350, 380, 1350, 735, 1300, 735, 1300, 420);
            fill(166, 180, 189);
            rect(1150, 420, 10, 40);

            //Design bangunan 2
            fill(87, 86, 86);
            rect(1095,500, 210, 10);
            fill(87, 86, 86);
            rect(1095,580, 210, 10);
            fill(87, 86, 86);
            rect(1095,660, 210, 10);

            quad(1305, 500, 1305, 510, 1350,470, 1350, 460);
            quad(1305, 580, 1305, 590, 1350,550, 1350, 540);
            quad(1305, 660, 1305, 670, 1350,630, 1350, 620);

            // Bangunan 1
            fill(166, 180, 189);
            rect(200, 320, 250, 405);
            fill(194, 200, 204);
            quad(450, 320, 400, 290, 160, 290, 200, 320);
            fill(122, 130, 135);
            quad(200, 320, 160, 290, 160, 725, 200, 725);

            //Jendela bangunan 2
            fill(206, 235, 66);
            rect(230, 350, 40, 40);
            rect(230, 400, 40, 40);
            rect(280, 350, 40, 40);
            rect(280, 400, 40, 40);

            rect(330, 350, 40, 40);
            rect(330, 400, 40, 40);
            rect(380, 350, 40, 40);
            rect(380, 400, 40, 40);

            rect(230, 500, 40, 40);
            rect(230, 550, 40, 40);
            rect(280, 500, 40, 40);
            rect(280, 550, 40, 40);

            rect(330, 500, 40, 40);
            rect(330, 550, 40, 40);
            rect(380, 500, 40, 40);
            rect(380, 550, 40, 40);

            fill(121, 131, 138);
            rect(230, 650, 40, 40);
            rect(230, 700, 40, 25);
            rect(280, 650, 40, 40);
            rect(280, 700, 40, 25);

            rect(330, 650, 40, 40);
            rect(330, 700, 40, 25);
            rect(380, 650, 40, 40);
            rect(380, 700, 40, 25);
        }
    }

    class Bayangan {
        void draw() {
            // Bayangan untuk bangunan 1
            fill(0, 0, 0, 50);
            rect(159, 725, 290, 20, 0, 0, 10, 10);

            // Bayangan untuk bangunan 2
            fill(0, 0, 0, 50);
            rect(1100, 735, 250, 20, 0, 0, 10, 10);

            // Bayangan untuk Monas
            fill(0, 0, 0, 50);
            rect(700, 730, 75, 100, 0, 0, 5, 5);
            rect(690, 830, 95, 20);
            triangle(700, 850, 742, 895, 775, 850);
        }
    }

    class Awan {
        float x, y;
        float speed;

        Awan() {
            x = random(width);
            y = random(50, height / 3);
            speed = random(0.1f, 0.5f);
        }

        void move() {
            x += speed;
            if (x > width) {
                x = 0;
                y = random(50, height / 3);
            }
        }

        void display() {
            fill(125, 125, 125, 250);
            noStroke();
            ellipse(x, y, 100, 50);
            ellipse(x + 30, y - 20, 80, 40);
            ellipse(x + 70, y, 100, 50);
        }
    }

    class JakartaText {
        void display() {
            fill(214, 230, 2, 127);
            PFont font;
            font = createFont("Arial Bold", 80);
            textFont(font);
            textAlign(CENTER, CENTER);
            text("J  A  K  A  R  T  A", 760, 200);
        }
    }

    class Lightning {
        float x1, y1, x2, y2;

        Lightning() {
            generateLightning();
        }

        void generateLightning() {
            x1 = random(width);
            y1 = 0;
            x2 = random(width);
            y2 = random(0, height / 3);
        }

        void display() {
            stroke(255, 255, 0);
            strokeWeight(4);
            line(x1, y1, x2, y2);
            generateLightning();

        }
    }

    class BintangKelapKelip {
        float x, y;
        int duration;
        boolean isOn;

        BintangKelapKelip(float x, float y, int duration) {
            this.x = x;
            this.y = y;
            this.duration = duration;
            this.isOn = true;
        }

        void update() {
            if (duration > 0) {
                if (frameCount % 1000 == 0) {
                    isOn = !isOn;
                    duration--;
                }
            }
        }

        void display() {
            if (isOn) {
                fill(255, 255, 0);
                noStroke();
                ellipse(x, y, 2, 2);
            }
        }
    }

    class Sekoci {
        float x;
        float y;

        Sekoci() {
            respawn();
        }

        void respawn() {
            x = width;
            y = random(750, height - 100);
        }

        void draw() {
            fill(252, 175, 8);
            ellipse(x, y, 150, 30);
            ellipse(x, y + 15, 150, 30);
            rect(x - 76, y + 2, 11, 13);
            rect(x + 65, y + 2, 11, 13);
            fill(99, 76, 26);
            ellipse(x, y + 1, 100, 30);
        }

        void move(float speed) {
            x -= speed;
        }
    }
}
