using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class Player_Score : MonoBehaviour {

    public float timeLeft = 120;
    public int playerScore = 0;
    public GameObject timeLeftUI;
    public GameObject playerScoreUI;
	
    void Start ()
    {
        DataManagement.datamanagement.LoadData();
    }

	// Update is called once per frame
	void Update () {
        timeLeft -= Time.deltaTime;
        timeLeftUI.gameObject.GetComponent<Text>().text = ("Time Left: " + (int)timeLeft);
        playerScoreUI.gameObject.GetComponent<Text>().text = ("Score: " + playerScore);
        if (timeLeft < 0.1f)
        {
            SceneManager.LoadScene("New Scene"); 
        }
	}

    private void OnTriggerEnter2D(Collider2D trig)
    {
        if(trig.gameObject.name == "EndLevel")
        {
            CountScore();
            DataManagement.datamanagement.SaveData();
        }
        if(trig.gameObject.tag == "lil bitch")
        {
            playerScore += 10;
            Destroy(trig.gameObject);
        }
    }

    void CountScore ()
    {
        Debug.Log("Data score: " + DataManagement.datamanagement.highScore);
        playerScore += (int)(timeLeft * 100);
        DataManagement.datamanagement.highScore = playerScore + (int)(timeLeft * 10);
        DataManagement.datamanagement.SaveData();
        Debug.Log("Save score: " + DataManagement.datamanagement.highScore);
    }
}
