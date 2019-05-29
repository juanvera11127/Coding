using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class EndLevel : MonoBehaviour {

    private string time;
    GameObject g;
    Scene x;
	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {

      //  time = GameObject.Find("Text");
	}
    private void OnTriggerEnter2D(Collider2D collision)
    {
        if(collision.gameObject.tag == "Player1")
        {
            x = SceneManager.GetActiveScene();
            if(x.name.Equals("a1"))
            {
                SceneManager.LoadScene("a2");
            }
            if(x.name.Equals("a2"))
            {
                SceneManager.LoadScene("a3");
            }
            if(x.name.Equals("a3"))
            {
                SceneManager.LoadScene("a4");
            }
            if(x.name.Equals("a4"))
            {
                SceneManager.LoadScene("a5");
            }
            if (x.name.Equals("a5"))
            {
                SceneManager.LoadScene("end");
            }

            Debug.Log("alsjdflasjflja");
            DontDestroyOnLoad(GameObject.Find("Text"));
           // SceneManager.LoadScene("a1");
        }
    }
}
