package com.cengek.thesamething.screens;
/*
 * 
 * YA AMPUUUN, jadi berantakan gini
 * seharusnya kalau tulisan ya tulisan aja diaturnya
 * terus kalau game isinya logic game aja
 * 
 * Harusnya dibikin juga kelas khusus untuk handling button
 * 
 * Nanti deh ya kedepannya kita perbaiki
 * 
 */


import java.io.IOException;
import java.util.Random;
import java.util.Vector;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.midlet.MIDlet;

import com.cengek.thesamething.main.GameMidlet;
import com.cengek.thesamething.manager.SameThingGame;
import com.cengek.thesamething.util.CharFonts;
import com.cengek.thesamething.util.RMS;

public class ScreenGame extends Screen {

	private final String GAME_BG = "/game_bg.png";
	private final String THINGS_SPR = "/things_spr.png";
	private final String HIGHLIGHT_SPR = "/highlight_spr.png";
	private final String BACK_BTN = "/back_btn.png";
	private final String TIMER = "/timer_rope.png";
	private final String TIMER_BG = "/timer_bg.png";
	private final String TIMER_COVER = "/timer_penutup.png";
	private final String HELP_SPR = "/help_spr.png";
	private final String GAME_OVER = "/game_over.png";
	private final String NOT_SAME = "/not_same.png";
	private final String TIME_UP = "/time_up.png";
	
	
	Image img_bg, img_thingsSpr, img_highlightSpr,
		  img_backBtn, img_timer, img_timerCover,
		  img_timerBg, img_helpSpr, img_gameOver,
		  img_notSame, img_timeUp;

	Sprite spr_btnBack, spr_timer, spr_timerCover,
		   spr_timerBg, spr_help, spr_gameOver,
		   spr_notSame, spr_timeUp;

	private final int MARGIN_TOP = 85;
	
	private boolean game_start = true;		//penanda game baru dimulai, untuk munculkan help
	private boolean game_over = false;		//penanda game selesai
	
	private int score = 0;
	private int level = 1;
	
	//waktu sebelum muncul tulisan game over
	private int countTillOver = 13;
	private int countNow = countTillOver;
	
	private int[][] arrayThings = { { 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1 } };

	private int[][] originX = { { 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1 } };

	private int[][] originY = { { 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1 } };

	private int[][] stateUp = { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0 } };

	private String[][] tulisanAkhir  = {{"New High", "Score!"},
										{"Well", "play!"},
										{"You are", "adorable"},
										{"We love", "you!"},
										{"Let's play in", "front of mirror!"}, 
										{"Play with", "your friend"}, 
										{"What time", "is it?"}, 
										{"Have you", "had a drink?"}, 
										{"Homework", "anybody?"},
										{"Naah, I love", "vegetables"},};
	
	Sprite thingsSprite, highlightSprite, highlightSprite2;

	// penanda sudah berapa yang dipencet
	private int thingsPressed = 0;

	// penanda untuk pengecekan
	private int firstPressed = -1;
	private int secondPressed = -1;

	private int firstPressedX = -1;
	private int firstPressedY = -1;

	private int secondPressedX = -1;
	private int secondPressedY = -1;

	private boolean timeOut = false;
	
	//waktu yang per level
	//waktu makin lama makin sedikit
	private int value_timer = 1000;
	
	//waktu yang sedang dihabiskan dan begerak.
	private int value_timer_now = 1000;
	
	//waktu yang dikurangi setiap levelnya
	private int decrease_per_level = 150;
	
	//waktu paling minimum pada level. Tidak ada waktu dibawah ini
	private int minimum_level = 250;
	
	private Vector vec_tulisanTxt;
	private Vector vec_scoreTxt;
	
	private int posXTulisan = 100;
	private int posYTulisan = 235;
	
	private int posXScoreTxt = 50;
	private int posYScoreTxt = 175;
			
	private boolean initGameOver = false;
	
	private Sprite[][] arrThingsSprite = new Sprite[][] {
			{ thingsSprite, thingsSprite, thingsSprite, thingsSprite,
					thingsSprite, thingsSprite },
			{ thingsSprite, thingsSprite, thingsSprite, thingsSprite,
					thingsSprite, thingsSprite },
			{ thingsSprite, thingsSprite, thingsSprite, thingsSprite,
					thingsSprite, thingsSprite },
			{ thingsSprite, thingsSprite, thingsSprite, thingsSprite,
					thingsSprite, thingsSprite },
			{ thingsSprite, thingsSprite, thingsSprite, thingsSprite,
					thingsSprite, thingsSprite },
			{ thingsSprite, thingsSprite, thingsSprite, thingsSprite,
					thingsSprite, thingsSprite } };

	public ScreenGame(Graphics g, MIDlet m, LayerManager lg, SameThingGame host) {
		// TODO Auto-generated constructor stub
		super(g, m, lg, host);


		init();

	}

	public void createBtnBack() {
		spr_btnBack = new Sprite(img_backBtn);

	}

	public void createGameOver(){
		spr_gameOver = new Sprite(img_gameOver);
	}
	
	public void createTimeUp(){
		spr_timeUp = new Sprite(img_timeUp);
	}
	
	public void putTimeUp(){
		selflg.append(spr_timeUp);
	}

	public void createHelp(){
		spr_help = new Sprite(img_helpSpr, 211, 154);
	}

	
	// lampu yang muncul pas ikon dipencet
	public void createHighlight() {
		highlightSprite = new Sprite(img_highlightSpr, 40, 40);
		highlightSprite.setFrame(0);
		highlightSprite.setVisible(false);
	}

	
	public void createHighlight2() {
		highlightSprite2 = new Sprite(img_highlightSpr, 40, 40);
		highlightSprite2.setFrame(0);
		highlightSprite2.setVisible(false);
	}
	
	public void createImages(){
		try {
			img_bg = Image.createImage(GAME_BG);
			img_thingsSpr = Image.createImage(THINGS_SPR);
			img_highlightSpr = Image.createImage(HIGHLIGHT_SPR);
			img_backBtn = Image.createImage(BACK_BTN);
			img_timer = Image.createImage(TIMER);
			img_timerBg = Image.createImage(TIMER_BG);
			img_timerCover = Image.createImage(TIMER_COVER);
			img_helpSpr = Image.createImage(HELP_SPR);
			img_gameOver = Image.createImage(GAME_OVER);
			img_notSame = Image.createImage(NOT_SAME);
			img_timeUp = Image.createImage(TIME_UP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createNotSame(){
		spr_notSame = new Sprite(img_notSame);
	}
	
	public void createThings() {

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				Sprite spr = new Sprite(img_thingsSpr, 40, 40);
				arrThingsSprite[i][j] = spr;
			}
		}
	}
	
	public void createTimer(){
		spr_timer = new Sprite(img_timer);
	}
	
	public void createTimerBg(){
		spr_timerBg = new Sprite(img_timerBg);
	}
	
	public void createTimerCover(){
		spr_timerCover = new Sprite(img_timerCover);
	}
	
	/**
	 * Membuat tulisan,1 baris maksimum 20 huruf
	 * @param teks
	 * @return vector sprite huruf
	 */
	private Vector createTulisan(String teks, String teks2) {

		Vector vec_huruf = new Vector();
		int nhuruf_baris = 20;
		
		for (int i = 0; i < nhuruf_baris; i++) {
			Image img_huruf = null;
			if (teks.length()-1 >= i) {
				img_huruf = CharFonts.drawCharImage(teks.charAt(i));
			}else{
				img_huruf = CharFonts.drawCharImage(' ');
			}
			Sprite spr_huruf = new Sprite(img_huruf);
			vec_huruf.addElement(spr_huruf);
		}
		
		for (int i = 0; i < nhuruf_baris; i++) {
			Image img_huruf = null;
			if (teks2.length()-1 >= i) {
				img_huruf = CharFonts.drawCharImage(teks2.charAt(i));
			}else{
				img_huruf = CharFonts.drawCharImage(' ');
			}
			Sprite spr_huruf = new Sprite(img_huruf);
			vec_huruf.addElement(spr_huruf);
		}
		
		
		return vec_huruf;
	}
	
	/**
	 * Membuat tulisan score
	 * @param teks
	 * @return vector sprite huruf
	 */
	private Vector createScore(String teks) {

		Vector vec_huruf = new Vector();
		
		for (int i = 0; i < 20; i++) {
			Image img_huruf = null;
			if (teks.length()-1 >= i) {
				img_huruf = CharFonts.drawCharImage(teks.charAt(i));
			}else{
				img_huruf = CharFonts.drawCharImage(' ');
			}
			Sprite spr_huruf = new Sprite(img_huruf);
			vec_huruf.addElement(spr_huruf);
		}
		
		return vec_huruf;
	}
	
	/**
	 * Mengurangi waktu yang berlalu
	 */
	public void decreaseTime(){
		
		if (value_timer_now > 0) {
			value_timer_now--;
		}
		
		
	}
	
	public void draw() {

		drawBackground();
		
		//tulisan di belakang

		//tulisan skor
		CharFonts.drawString(g, "Score : " + score, 5, host.getHeight()-36);
		//tulisan level
		CharFonts.drawString(g, "Level : " + level, 5, host.getHeight()-18);
		
		
		host.setLg(selflg);
		
		//CharFonts.drawString(g, String.valueOf(host.getGameSound().playerTake.getState()), 50, 50);
		
	}
	
	public void drawBackground() {
		g.drawImage(img_bg, 0, 0, 0);
	}

	/**
	 * Method mendapatkan things apa yang sudah terpilih.
	 * Belum dipakai di dalam sih, siapa tahu dipakai nanti
	 * @return indeks thing terpilih
	 */
	public int getThingsPressed() {
		return thingsPressed;
	}

	private void hideTulisan(Vector vec_huruf){
		for (int i = 0 ; i <vec_huruf.size(); i++) {
			((Sprite)vec_huruf.elementAt(i)).setVisible(false);
		}
	}

	public void init() {
		createImages();

		//tulisan
		vec_tulisanTxt = createTulisan("Let's play in", "front of mirror");
		//tulisan ditaruh dimana 
		putTulisan(vec_tulisanTxt, posXTulisan, posYTulisan);
		hideTulisan(vec_tulisanTxt);
		
		//score
		vec_scoreTxt = createScore("Your Score: " + score);
		//score ditaruh dimana
		putScore(vec_scoreTxt, posXScoreTxt, posYScoreTxt);
		hideTulisan(vec_scoreTxt);
		
		//Help
		createHelp();
		putHelp();
		
		//Game over
		createGameOver();
		putGameOver();
		
		//Time's up
		createTimeUp();
		putTimeUp();
		
		//not same
		createNotSame();
		putNotSame();
		
		//Timer
		createTimer();
		createTimerBg();
		createTimerCover();

		putTimerCover();
		putTimer();
		putTimerBg();
		
		//Highlight
		createHighlight();
		putHighlight(); // menaruh yang nandain things dipilih
		
		//Highlight
		createHighlight2();
		putHighlight2(); // menaruh yang nandain things dipilih
		
		//Things
		createThings();
		putThings();


		//Button back
		createBtnBack();
		putBtnBack();
		

		
		//hal custom lainnya
		spr_btnBack.setPosition((host.getWidth() / 2) - (spr_btnBack.getWidth() / 2),
				(host.getHeight() - spr_btnBack.getHeight())+15);
		
		spr_timer.setPosition(0, 0);
		spr_timerBg.setPosition(0, 0);
		spr_timerCover.setPosition(host.getWidth(), 0);
		
		spr_help.setPosition((host.getWidth()/2)-(spr_help.getWidth()/2), (host.getHeight()/2)-(spr_help.getHeight()/2));
		spr_help.setFrame(0);
		
		spr_gameOver.setPosition((host.getWidth()/2)-(spr_gameOver.getWidth()/2), (host.getHeight()/2)-(spr_help.getHeight()/2));
		
		spr_notSame.setPosition((host.getWidth()/2)-(spr_notSame.getWidth()/2), spr_timerCover.getHeight() + 5);
		spr_timeUp.setPosition((host.getWidth()/2)-(spr_timeUp.getWidth()/2), spr_timerCover.getHeight() + 5);
		
		
	}
	
	/**
	 * Mendapatkan status waktu habis
	 */
	public boolean isTimeOut(){
		return timeOut;
	}
	
	public void levelUp(){
		
		//tambah skornya
		addScorePerLevel();
		
		//naikkan levelnya
		level++;
		
		//kurangi waktunya
		if ((value_timer - decrease_per_level) >= minimum_level) {
			value_timer -= decrease_per_level;
			value_timer_now = value_timer;
		}
		
		
		
		
	}
	
	/**
	 * Method menggerakkan semua things ke bawah
	 */
	public void moveDown() {

		if (thingsPressed <= 0) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++) {
					if (arrThingsSprite[i][j].getY() != originY[i][j]) {

						(arrThingsSprite[i][j]).move(0,
								-((arrThingsSprite[i][j]).getY()
										- originY[i][j] - 30) / 5);

						if ((originY[i][j] - arrThingsSprite[i][j].getY()) < 30) {

							arrThingsSprite[i][j].setPosition(originX[i][j],
									originY[i][j]);
						}
					}
				}
			}
		}

	}
	
	/**
	 * Method menggerakkan things terpilih ke atas
	 */
	public void moveUpSelected() {
		if (thingsPressed > 0) {

			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++) {

					if (stateUp[i][j] == 1) {
						if (arrThingsSprite[i][j].getY() != -80) {
							arrThingsSprite[i][j]
									.move(-((arrThingsSprite[i][j].getX() - (100)) / 8),
											-((arrThingsSprite[i][j].getY() - (-85)) / 8));

							if ((arrThingsSprite[i][j].getY() - (-80)) < 5) {
								arrThingsSprite[i][j].setPosition(
										arrThingsSprite[i][j].getX(), -80);
							}
						}
					}

				}
			}
		}
	}
	
	/**
	 * Method mengecek saat layar disentuh
	 */
	public void pointerPressed(int x, int y) {
		System.out.println("xy = " + x + ":" + y);

		
		

	}
	
	public void pointerReleased(int x, int y) {
		// TODO Auto-generated method stub
		if (game_start) {
			
			//menampilkan help
			if (spr_help.getFrame() < 1){
				spr_help.setFrame(1);
			}else{
				spr_help.setFrame(0);
				spr_help.setVisible(false);
				game_start = false;
				setThingsVisible();
			}
			
		}else{
			
			// jika memencet tombol back
			if (x >= spr_btnBack.getX() && x <= spr_btnBack.getX() + spr_btnBack.getWidth()
					&& y >= spr_btnBack.getY()
					&& y <= spr_btnBack.getY() + spr_btnBack.getHeight()) {
				//removeSprites();
	
				// tuju ke elemen yang kita inginkan
				// element at 0 untuk vector screen adalah menunya
				host.setCurrentScreen((Screen) host.getScreenVector().elementAt(0));
				((ScreenMain)host.getCurrentScreen()).start();
				
				
				//mematikan player suara game over
				host.getGameSound().stopGameOver();
			}
						
			if (! game_over) {
			
	
				int idx_x = -1;
				int idx_y = -1;
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 6; j++) {
						if (x > (i * 40) && x < ((i + 1) * 40)
								&& y > ((j * 40) + MARGIN_TOP)
								&& y < (((j + 1) * 40) + MARGIN_TOP)) {
							System.out.println("pencet indeks ke - " + i + "," + j);
							idx_x = i;
							idx_y = j;
						}
					}
		
				}
		
				// jika memencet things
				if (idx_x > -1 && idx_y > -1
						&& (arrThingsSprite[idx_x][idx_y]).isVisible()) {
					
					if (host.getGameSettings().isMusicOn()) {
						//bunyi dipencet
						host.getGameSound().playTake();
					}
					
					
					//jika belum pernah mencet
					if (firstPressed == -1) {
						
						//jika yang dipencet adalah things yang belum dipencet
						if (stateUp[idx_x][idx_y] == 0) {
		
							firstPressed = (arrThingsSprite[idx_x][idx_y]).getFrame();
							firstPressedX = idx_x;
							firstPressedY = idx_y;
							// posisikan highlight
							highlightSprite.setPosition(
									(originX[idx_x][idx_y]),
									(originY[idx_x][idx_y]));
							highlightSprite.setVisible(true);
						}
						
						
					//jika sudah dipencet (berarti sekarang cek yang pasangannya)
					} else {
		
						// mencegah sama yang dipencet
						if (!(idx_x == firstPressedX && idx_y == firstPressedY)) {
		
							secondPressed = (arrThingsSprite[idx_x][idx_y]).getFrame();
							secondPressedX = idx_x;
							secondPressedY = idx_y;
		
							System.out.println("First = " + firstPressed
									+ ", Second = " + secondPressed);
		
							// kalau sama berarti benar
							if (firstPressed == secondPressed) {
		
								stateUp[firstPressedX][firstPressedY] = 1;
								stateUp[secondPressedX][secondPressedY] = 1;
		
								thingsPressed += 2;
								
								// direset semua parameternya
								firstPressed = -1;
								secondPressed = -1;
		
								firstPressedX = -1;
								firstPressedY = -1;
								secondPressedX = -1;
								secondPressedY = -1;
		
								highlightSprite.setVisible(false);
								
								addScorePerTrue();
								
							} else {
								
								//jika yang dipencet adalah things yang belum dipencet
								if (stateUp[idx_x][idx_y] == 0) {
									// berarti tidak benar
									
									highlightSprite2.setPosition(
											(originX[idx_x][idx_y]),
											(originY[idx_x][idx_y]));
									highlightSprite2.setVisible(true);
									
									System.out.println("SALAH");
									
									spr_notSame.setVisible(true);
									game_over = true;
									initGameOver = true;
								}
								
								
							}
						}
		
					}
				}
			}
		}
	}

	public void setGameOverMessage(){
		
		
		//kalau high score berikan tampilan selamat
		if (isHighestScore()) {
			//posisi highest score ada di array tulisanAkhir
			setTulisan(tulisanAkhir[0][0], tulisanAkhir[0][1]);
			
			//masukkan ke highest score
			setCurrentHighestScore();
			
		}else{
			int random;
			Random randomNumber = new Random();
			randomNumber.setSeed(System.currentTimeMillis());
			random = randomNumber.nextInt()%(tulisanAkhir.length);
			random = Math.abs(random);
			
			//jangan sampai mengenai high score
			if (random == 0) {
				random = 1;
			}
			setTulisan(tulisanAkhir[random][0], tulisanAkhir[random][1]);
		}
		
		setScore("Your Score: " + score);
		
		
	}
	
	public void setCurrentHighestScore(){
		RMS record = ((GameMidlet)getMidlet()).getRMS();
		record.saveScore(score);
	}
	
	public boolean isHighestScore(){
		RMS record = ((GameMidlet)getMidlet()).getRMS();
		int highestScore = record.getScore();
		
		if (score > highestScore) {
			return true;
		}else{
			return false;
		}
	}
	
	public void putBtnBack(){
		selflg.append(spr_btnBack);
	}
	
	public void putGameOver(){
		selflg.append(spr_gameOver);
	}

	public void putHelp(){
		selflg.append(spr_help);
	}
	
	/**
	 * Method menaruh highlight things terpilih pada Layer Manager
	 */
	public void putHighlight() {
		selflg.append(highlightSprite);
	}
	
	public void putHighlight2() {
		selflg.append(highlightSprite2);
	}
	
	public void putNotSame(){
		selflg.append(spr_notSame);
	}
	
	/**
	 * method menaruh things pada Layer Manager
	 */
	public void putThings() {
		for (int i = 5; i >= 0; i--) {
			for (int j = 5; j >= 0; j--) {

				originX[i][j] = i * 40;
				originY[i][j] = (j * 40) + MARGIN_TOP;

				(arrThingsSprite[i][j]).setPosition(i * 40, (j * 40)
						+ MARGIN_TOP);
				selflg.append((arrThingsSprite[i][j]));
			}
		}

	}
	
	public void putTimer(){
		selflg.append(spr_timer);
	}
	
	public void putTimerBg(){
		selflg.append(spr_timerBg);
	}

	public void putTimerCover(){
		selflg.append(spr_timerCover);
	}

	private void putTulisan(Vector vec_huruf, int posisix, int posisiy) {
		int posx_huruf = posisix;
		for (int i = 0; i < 20; i++) {
			Sprite elemen_huruf = (Sprite)(vec_huruf.elementAt(i));
			
			elemen_huruf.setPosition(posx_huruf, posisiy);
			posx_huruf += elemen_huruf.getWidth();
			
			selflg.append(elemen_huruf);
		}
		
		posx_huruf = posisix;
		for (int i = 20; i < 40; i++) {
			Sprite elemen_huruf = (Sprite)(vec_huruf.elementAt(i));
			
			elemen_huruf.setPosition(posx_huruf, posisiy+18);
			posx_huruf += elemen_huruf.getWidth();
			
			selflg.append(elemen_huruf);
		}
		
	}
	
	private void putScore(Vector vec_huruf, int posisix, int posisiy) {
		int posx_huruf = posisix;
		for (int i = 0; i < 20; i++) {
			Sprite elemen_huruf = (Sprite)(vec_huruf.elementAt(i));
			
			elemen_huruf.setPosition(posx_huruf, posisiy);
			posx_huruf += elemen_huruf.getWidth();
			
			selflg.append(elemen_huruf);
		}
		
	}
	
	/**
	 * Method mengacak posisi things
	 */
	public void randomizeThingsPlace() {

		int random;
		Random randomNumber = new Random();
		randomNumber.setSeed(System.currentTimeMillis());
		random = randomNumber.nextInt();

		Vector vec = new Vector();

		int[] arr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
				17 };

		for (int i = 0; i < arr.length; i++) {
			vec.addElement(new Integer(i));
			vec.addElement(new Integer(i));
		}

		random = Math.abs(random);

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				randomNumber.setSeed(System.currentTimeMillis());
				random = randomNumber.nextInt();

				random = Math.abs(random);

				random = random % (vec.size());
				Integer int_value = (Integer) vec.elementAt(random);
				arrayThings[i][j] = int_value.intValue();

				vec.removeElementAt(random);
			}
		}

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(arrayThings[i][j] + "-");
			}
			System.out.println();
		}

	}
	
	/**
	 * Prosedur yang dipanggil saat semua
	 * things sudah dipilih agar muncul lagi
	 */
	public void reappear() {
		thingsPressed = 0;
		
		randomizeThingsPlace();
		setThingsFrame();
		setThingsVisible();
		setThingsTop();
		setStateUpZero();

		levelUp();

		
	}


	/**
	 * Prosedur untuk menghilangkan sprites di screen
	 */
	public void removeSprites() {

		// menghilangkan things
		for (int i = 5; i >= 0; i--) {
			for (int j = 5; j >= 0; j--) {


				originX[i][j] = i * 40;
				originY[i][j] = (j * 40) + MARGIN_TOP;

				(arrThingsSprite[i][j]).setPosition(i * 40, (j * 40)
						+ MARGIN_TOP);
				selflg.remove((arrThingsSprite[i][j]));
			}
		}

		// menghilangkan highlight
		selflg.remove(highlightSprite);
		
		// menghilangkan tombol
		selflg.remove(spr_btnBack);
	}

	public void run() {
		
		updateTimer();
		if (!game_start) {
			if (!game_over) {
				//check game over by time out
				if (isTimeOut()) {
					spr_timeUp.setVisible(true);
					
					game_over = true;
					initGameOver = true;
				}
				
				if (getThingsPressed() >= 36) {
					reappear();
				}
				moveDown();
				moveUpSelected();
				
				decreaseTime();
			}else{
				
				//untuk mengupdate tampilan game over
				if (initGameOver) {
					initGameOver = false;
					//suara game over
					if (host.getGameSettings().isMusicOn()) {
						host.getGameSound().playGameOver();
					}
					
					
					setGameOverMessage();
					
				}
				
				//tampilkan setelah delay waktu game over habis
				if (countNow <= 0) {
					spr_gameOver.setVisible(true);
					
					//tampilkan tulisan unyu unyu
					showTulisan(vec_tulisanTxt);
					
					//tampilkan tulisan skor
					showTulisan(vec_scoreTxt);
					
				}else{
					//tunggu delay waktu game over
					countNow--;
				}
			}
			
		}
		
	}

	/**
	 * Membuat status dari setiap things menjadi tidak terpencet lagi Berguna
	 * saat diinisialisasi ulang
	 */
	public void setStateUpZero() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				stateUp[i][j] = 0;
			}
		}
	}

	/**
	 * Mengatur things memiliki posisi sesuai dengan nomor yang diacak
	 */
	public void setThingsFrame() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				(arrThingsSprite[i][j]).setFrame(arrayThings[i][j]);
			}
		}
	}

	public void setThingsPressed(int tp) {
		thingsPressed = tp;
	}

	/**
	 * Memposisikan things ke atas
	 */
	public void setThingsTop() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				(arrThingsSprite[i][j]).setPosition(
						originX[i][j],
						originY[i][j] - 150);
			}
		}
	}

	/**
	 * Membuat things tidak tampil semuanya
	 */
	public void setThingsUnvisible() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				(arrThingsSprite[i][j]).setVisible(false);
			}
		}
	}

	
	/**
	 * Membuat things tampil semuanya
	 */
	private void setThingsVisible() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				(arrThingsSprite[i][j]).setVisible(true);
			}
		}
	}

	
	/**
	 * Untuk menentukan kecepatan waktu berikutnya
	 */
	public void setTimerValue(int time){
		this.value_timer = time;
	}

	private void setTulisan(String teks, String teks2) {

		int posx_huruf = posXTulisan;
		
		for (int i = 0; i < 20; i++) {
			Image img_huruf = null;
			if (teks.length()-1 >= i) {
				img_huruf = CharFonts.drawCharImage(teks.charAt(i));
			}else{
				img_huruf = CharFonts.drawCharImage(' ');
			}
			
			Sprite elemen_huruf = (Sprite)(vec_tulisanTxt.elementAt(i));
			
			elemen_huruf.setPosition(posx_huruf, posYTulisan);
			posx_huruf += img_huruf.getWidth();
			
			((Sprite)vec_tulisanTxt.elementAt(i)).setImage(img_huruf, img_huruf.getWidth(), img_huruf.getHeight());
		}
		
		posx_huruf = posXTulisan;
		for (int i = 20; i < 40; i++) {
			Image img_huruf = null;
			if (teks2.length()-1 >= (i-20)) {
				img_huruf = CharFonts.drawCharImage(teks2.charAt((i-20)));
			}else{
				img_huruf = CharFonts.drawCharImage(' ');
			}
			
			Sprite elemen_huruf = (Sprite)(vec_tulisanTxt.elementAt(i));
			
			elemen_huruf.setPosition(posx_huruf, posYTulisan+18);
			posx_huruf += img_huruf.getWidth();
			
			((Sprite)vec_tulisanTxt.elementAt(i)).setImage(img_huruf, img_huruf.getWidth(), img_huruf.getHeight());
		}
		
		
	}
	
	private void setScore(String teks) {

		int posx_huruf = posXScoreTxt;
		
		for (int i = 0; i < 20; i++) {
			Image img_huruf = null;
			if (teks.length()-1 >= i) {
				img_huruf = CharFonts.drawCharImage(teks.charAt(i));
			}else{
				img_huruf = CharFonts.drawCharImage(' ');
			}
			
			Sprite elemen_huruf = (Sprite)(vec_scoreTxt.elementAt(i));
			
			elemen_huruf.setPosition(posx_huruf, posYScoreTxt);
			posx_huruf += img_huruf.getWidth();
			
			((Sprite)vec_scoreTxt.elementAt(i)).setImage(img_huruf, img_huruf.getWidth(), img_huruf.getHeight());
		}
		
	}
	

	private void showTulisan(Vector vec_huruf){
		for (int i = 0 ; i <vec_huruf.size(); i++) {
			((Sprite)vec_huruf.elementAt(i)).setVisible(true);
		}
	}

	/**
	 * Method untuk memulai sesi game yang baru
	 */
	public void start(){
		if (host.getGameSettings().isMusicOn()) {
			host.getGameSound().stopBGM();
		}
		
		
		setThingsUnvisible();
		
		randomizeThingsPlace();
		setThingsTop();
		setStateUpZero();
		setThingsFrame();
		
		thingsPressed = 0;
		firstPressed = -1;
		secondPressed = -1;

		firstPressedX = -1;
		firstPressedY = -1;

		secondPressedX = -1;
		secondPressedY = -1;
		
		value_timer = 1000;
		value_timer_now = 1000;
		timeOut = false;
		
		countNow = countTillOver;
		
		score = 0;
		level = 1;
		
		game_start = true;
		game_over = false;
		
		highlightSprite.setVisible(false);
		highlightSprite2.setVisible(false);
		spr_help.setVisible(true);
		
		spr_gameOver.setVisible(false);
		spr_notSame.setVisible(false);
		spr_timeUp.setVisible(false);
		hideTulisan(vec_tulisanTxt);
		hideTulisan(vec_scoreTxt);
		
	}

	/**
	 * Menggerakkan timer
	 */
	public void updateTimer(){

		if (value_timer_now <= 0) {
			timeOut = true;
		}
		
		double x = host.getWidth()*((double)value_timer_now/(double)value_timer);

		int position = (int)Math.ceil(x);;
		
		spr_timerCover.setPosition(position, 0);
	}
	
	
	/**
	 * Nilai skor setiap benar naik setiap levelnya
	 */
	public void addScorePerTrue(){
		score += level;
	}
	
	/**
	 * Nilai skore bertambah setiap level
	 */
	public void addScorePerLevel(){
		score += scoreTime();
	}
	
	/**
	 * Waktu yang berlebih memiliki nilai untuk score
	 * Persen waktu yang tersisa dikali 100 adalah skor waktu
	 * Pembulatan ke atas
	 * @return
	 */
	public int scoreTime(){
		double vtn =  (double)value_timer_now;
		double vt = (double)value_timer;
		
		double percent = (double)(100*(vtn/vt));
		percent = Math.ceil(percent);
		
		int scoreFromTime = (int)percent;
		System.out.println("Score from time: " + scoreFromTime);
		
		return scoreFromTime;
	}
	
}
