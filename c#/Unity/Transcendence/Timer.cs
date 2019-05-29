using System.Collections;
using UnityEngine.UI;
using UnityEngine;

public class Timer : MonoBehaviour {

    public Text timerText;
    private float startTime;

	// Use this for initialization
	void Start () {
        startTime = Time.time;
	}
	
	// Update is called once per frame
	void Update () {
        float t = Time.time - startTime;

        string minutes = ((int)t / 60).ToString();
        string seconds = (t % 60).ToString("f2");

        timerText.text = minutes + ":" + seconds;
	}

    //make a class called WinBox. Add a OnTriggerEnter(collider other). {
    //  GameObject.Find("Knight").SendMessage("Finish"); }
}
