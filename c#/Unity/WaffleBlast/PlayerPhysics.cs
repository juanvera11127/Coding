using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerPhysics : MonoBehaviour {

    private Rigidbody2D rb;

	// Use this for initialization
	void Start () {
        rb = GameObject.FindGameObjectWithTag("Player").GetComponent<Rigidbody2D>();
        rb.AddForce(new Vector3(4000, 1500, 0));
	}
	
	// Update is called once per frame
	void Update () {
        Camera.main.transform.position = new Vector3(rb.position.x, rb.position.y, -10 -rb.position.y/8 - rb.velocity.x / 20);
	}

    private void OnTriggerEnter2D(Collider2D collision)
    {
        Debug.Log(rb.velocity.y + ", " + rb.velocity.x);
        switch(collision.tag)
        {
            case "syrup" :
                rb.velocity = new Vector3(rb.velocity.x / 1.5f, rb.velocity.y / 2, 0);
                break;
            case "coin" :
                Debug.Log("Score++");
                Destroy(collision.gameObject);
                break;
            case "egg" :
                if(rb.velocity.y > 25)
                {
                    rb.velocity = new Vector3(rb.velocity.x * 1.2f, rb.velocity.y * 3, 0);
                }
                else
                {
                    rb.velocity = new Vector3(rb.velocity.x * 1.2f, 100, 0);
                }
                break;
        }
    }
}
