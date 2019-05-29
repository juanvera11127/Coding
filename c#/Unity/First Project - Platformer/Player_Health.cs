using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Player_Health : MonoBehaviour {

    public int health;

	// Use this for initialization
	void Start () {
        
	}
	
	// Update is called once per frame
	void Update () {
        if (gameObject.transform.position.y < -3){
            Die();
        }
	}

    void Die () {
        SceneManager.LoadScene("New Scene");
    }

    void OnCollisionEnter2D(Collision2D col)
    {
        if (col.gameObject.tag == "lil bitch")
        {
          //  Die();
        }
    }
}
