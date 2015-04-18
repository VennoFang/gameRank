package edu.cmu.sv.gamerank.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.cmu.sv.gamerank.model.AllGamesLocal;
import edu.cmu.sv.gamerank.model.Player;

public class Convert {
	
	public static AllGamesLocal readFile(String path)
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}
		String sCurrentLine;
		try {
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
