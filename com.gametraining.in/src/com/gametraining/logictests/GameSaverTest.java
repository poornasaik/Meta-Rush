package com.gametraining.logictests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameSaverTest {
	public static void main(String[] args) {
		
		playerScore ps = loadData();
		if(ps==null) {
			ps = new playerScore();
		}
		for(int i=0; i<10;i++) {
		ps.setCoins(50+i);
		ps.setHighScore(100+i);
		saveData(ps);
		}
		
		ps = loadData();
		
		System.out.println(ps.getCoins()+" and "+ps.getHighScore());
		
	}
	
	public static void saveData(playerScore ps) {
		
		try(FileOutputStream fs = new FileOutputStream("sc.raw")){
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(ps);
			os.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static playerScore loadData() {
		playerScore ps=null;
		try(FileInputStream fi = new FileInputStream("sc.raw")){
			ObjectInputStream oi = new ObjectInputStream(fi);
			ps= (playerScore)oi.readObject();
		} catch (FileNotFoundException e1) {
			System.out.println("Previous Game Data is not available");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ps;
	}
}
