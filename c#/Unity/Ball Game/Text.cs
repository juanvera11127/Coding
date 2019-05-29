using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Text : MonoBehaviour {
    private TextMesh levelText;
    private Rigidbody rb;
    public static bool isRunning = false;

	// Use this for initialization
	void Start () {
        levelText = GetComponent<TextMesh>();
        rb = GetComponent<Rigidbody>();
	}
	
	// Update is called once per frame
	void Update () {
        if(isRunning)
        {
            levelText.text = "Level " + Player.level;
            gameObject.transform.position = new Vector3(-3, -2, 50);
            rb.velocity = new Vector3(0, 0, -Player.speed);
            isRunning = false;
        }
        if(gameObject.transform.position.z < -12)
        {
            rb.velocity = Vector3.zero;
        }

	}

    public static void Initialize() 
    {
        isRunning = true;
    }
}
